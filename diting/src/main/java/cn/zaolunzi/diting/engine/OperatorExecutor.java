package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.GroupingStrategy;
import cn.zaolunzi.diting.api.Operator;
import org.apache.commons.lang3.SerializationUtils;


/**
 * 操作器组件的执行者。 当执行器启动时，会创建一个新线程来重复调用算子组件的apply()函数。
 *
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class OperatorExecutor extends ComponentExecutor {
    private Operator operator;
    
    public OperatorExecutor(Operator operator) {
        super(operator); this.operator = operator; for (int i = 0; i < operator.getParallelism(); ++i) {
            Operator cloned = SerializationUtils.clone(operator);
            instanceExecutors[i] = new OperatorInstanceExecutor(i, cloned);
        }
    }
    
    @Override
    public void start() {
        if (instanceExecutors != null) {
            for (InstanceExecutor executor : instanceExecutors) {
                executor.start();
            }
        }
    }
    
    public GroupingStrategy getGroupingStrategy() {
        return operator.getGroupingStrategy();
    }
}
