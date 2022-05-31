package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Component;
import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.Operator;
import cn.zaolunzi.diting.api.Source;
import cn.zaolunzi.diting.api.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class JobStarter {
    private final static int QUEUE_SIZE = 64;
    
    // 要启动的作业
    private final Job job;
    // 执行器集合
    private final List<ComponentExecutor> executorList = new ArrayList<ComponentExecutor>();
    private final List<EventDispatcher> dispatcherList = new ArrayList<EventDispatcher>();
    
    // 组件执行器之间的连接
    private final List<Connection> connectionList = new ArrayList<Connection>();
    private final Map<Operator, OperatorExecutor> operatorMap = new HashMap<Operator, OperatorExecutor>();
    private final Map<OperatorExecutor, EventQueue> operatorQueueMap = new HashMap<OperatorExecutor, EventQueue>();
    
    public JobStarter(Job job) {
        this.job = job;
    }
    
    public void start() {
        // 为所有组件设置执行器。
        setupComponentExecutors();
        
        // 现在所有组件都已创建。 建立连接以将组件连接在一起。
        setupConnections();
        
        // 启动所有进程
        startProcesses();
        
        // 启动web服务器
        new WebServer(job.getName(), connectionList).start();
    }
    
    /**
     * 创建所有 source 和 operator 执行程序。
     */
    private void setupComponentExecutors() {
        // 从job中的source开始，遍历组件创建executor
        for (Source source : job.getSources()) {
            SourceExecutor executor = new SourceExecutor(source); 
            executorList.add(executor);
            // 对于每个 source，遍历与其相关的操作。
            traverseComponent(source, executor);
        }
    }
    
    /**
     * 在所有组件执行器之间建立连接（中间队列）。
     */
    private void setupConnections() {
        for (Connection connection : connectionList) {
            connectExecutors(connection);
        }
    }
    
    /**
     * 启动作业的所有进程。
     */
    private void startProcesses() {
        Collections.reverse(executorList); for (ComponentExecutor executor : executorList) {
            executor.start();
        } for (EventDispatcher dispatcher : dispatcherList) {
            dispatcher.start();
        }
    }
    
    private void connectExecutors(Connection connection) {
        // 它是一个新连接的算子执行器。 请注意，在此版本中，没有共享的“from”组件和“to”组件。 该作业看起来像一个单链表。
        // 每个组件执行器可以连接到多个下游算子执行器。
        // 对于每一个下游算子执行器，都有一个流管理器。
        // 上游执行器的每个实例执行器首先连接到下游执行器的所有流管理器。 每个流管理器连接到下游执行器的所有实例执行器。
        // 注意在这个版本中，没有共享的“from”组件和“to”组件。
        // 作业看起来像一个单链表。
        connection.from.registerChannel(connection.channel);
        if (operatorQueueMap.containsKey(connection.to)) {
            // Existing operator. Connect to upstream only.
            EventQueue dispatcherQueue = operatorQueueMap.get(connection.to);
            connection.from.addOutgoingQueue(connection.channel, dispatcherQueue);
        } else {
            // New operator. Create a dispatcher and connect to upstream first.
            EventDispatcher dispatcher = new EventDispatcher(connection.to);
            EventQueue dispatcherQueue = new EventQueue(QUEUE_SIZE);
            operatorQueueMap.put(connection.to, dispatcherQueue);
            dispatcher.setIncomingQueue(dispatcherQueue);
            connection.from.addOutgoingQueue(connection.channel, dispatcherQueue);
        
            // Connect to downstream (to each instance).
            int parallelism = connection.to.getComponent().getParallelism();
            EventQueue[] downstream = new EventQueue[parallelism];
            for (int i = 0; i < parallelism; ++i) {
                downstream[i] = new EventQueue(QUEUE_SIZE);
            }
            connection.to.setIncomingQueues(downstream);
            dispatcher.setOutgoingQueues(downstream);
        
            dispatcherList.add(dispatcher);
        }
    }
    
    private void traverseComponent (Component component, ComponentExecutor executor){
        Stream stream = component.getOutgoingStream();
        
        for (String channel : stream.getChannels()) {
            for (Operator operator : stream.getAppliedOperators(channel)) {
                OperatorExecutor operatorExecutor;
                if (!operatorMap.containsKey(operator)) {
                    operatorExecutor = new OperatorExecutor(operator);
                    operatorMap.put(operator, operatorExecutor);
                    executorList.add(operatorExecutor);
                    
                    // Setup executors for the downstream operators.
                    traverseComponent(operator, operatorExecutor);
                } else {
                    operatorExecutor = operatorMap.get(operator);
                }
                connectionList.add(new Connection(executor, operatorExecutor, channel));
            }
        }
    }
}
