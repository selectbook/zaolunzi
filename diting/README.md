# diting (谛听)

streaming system(谛听 流计算系统)

流式系统是大数据领域中最热门的话题，
选择一个好的流式系统开源项目，并逐步深入研究，是掌握流式系统的核心法门。

2017年我在一家车联网公司使用的阿里巴巴改进后的Jstorm做流处理


纵观业内大规模采用的流式系统开源项目，storm,sparkstreaming,flink，flink是一个不错的选择。
但是flink的源码已经较为庞杂，想要吃透flink，已经较为困难。

这就涉及到如何选择一个好的开源项目，
我会选择一个新兴的（新兴的意味着该项目目前的代码复杂度不是很高，相对来说比较容易理解），
有不少成熟的工业界使用案例（有大公司采用最好），在github较为流行（star数一千以上），
社区活跃度很高(贡献者较多，pr提交频繁，issue较多，深度参与该项目的机会也就更多)。

在 github 上搜索 Stream processing ，比较一番过后，我认为比较值得参与的一个开源项目是 risingwave
这个项目是 rust 语言写的，首先需要了解rust

目前我在参与的是 rocketmq 的子项目 **rocketmq-streams**，希望一段时候后，能成为这个项目的主要贡献者。

另一个途径是参考先进的流式系统，自己实现一个最小化可用的流式系统，由此便诞生了本项目。

谛听是一个mini版的流式系统，但是实现了流式系统的核心概念。

Core streaming concepts：
流系统的核心概念：
event, 
job, 
source, 
operator, 
stream

Queue 队列在所有流系统中大量使用
传统的分布式系统通常是请求/响应模型（也称为同步模型）进行通信。对于流式系统，请求/响应模型在处理实时数据时会引入不必要的延迟。
在高层次上，分布式流系统与系统中的组件保持长时间运行的连接，以减少数据传输时间。
这种长时间运行的连接用于持续传输数据，并允许流系统在事件发生时对其迅速作出反应。

流式作业执行流程

* 传感器读取器从传感器读入数据并将事件存储在队列中，它就是 source。
* 车辆计数器负责对通过流的车辆进行计数。它是一个 operator。
* 数据从 source 到 operator 的连续移动是车辆事件流(stream of vehicle event)。

第一个流作业

通用步骤：
* 创建一个event类.

一个事件是一个单一的流中要由作业处理的数据。在diting框架中，API类Event负责存储或包装用户数据。其他流系统也会有类似的概念。
在该作业中，每个事件都代表一种车辆类型。现在为了简单起见，每种车辆类型都只是一个类似于car或者truck的字符串。
我们将VehicleEvent用作事件类的名称，它是从EventAPI 中的类扩展而来的。每个VehicleEvent对象都包含可以通过该getData()函数检索的车辆信息。

* 创建 source

一个来源是零件将来自外部世界的数据带入流式传输系统。
在您的流式传输作业中，传感器读取器将车辆类型数据从本地端口接收到系统中。

所有流式传输框架都有一个 API，使您能够编写只有您关心的数据源逻辑。
所有数据源 API 都有某种类型的生命周期钩子，将被调用以接受来自外部世界的数据。这是您的代码将由框架执行的地方。

什么是生命周期钩子？

软件框架中的生命周期钩子是它们所在的框架以某种可重复模式调用的方法。
通常，这些方法允许开发人员自定义他们的应用程序在他们构建应用程序的框架的生命周期阶段的行为方式。
在 diting 框架的情况下，我们有一个生命周期钩子（或方法），称为getEvents(). 它被框架连续调用，
以允许您从外部世界中提取数据。Lifecyle 钩子允许开发人员编写他们关心的逻辑，并让框架处理所有重复的工作。

传感器阅读器将从传感器读取事件。在本练习中，您将通过自己创建事件并将它们发送到流作业正在侦听的计算机上的开放端口来模拟桥传感器。
您发送到端口的车辆类型将被传感器读取器拾取并发送到流作业中，以显示处理无限（或无界）事件流的情况。

* 创建 operator.

  为了让您的工作简单，我们只有一个来源和一个操作员。车辆计数器的当前实现是仅对车辆进行计数，然后将当前计数记录在系统中。
  实现该系统的另一种可能更好的方法是让车辆计数器将车辆排放到新的流中。然后，可以在车辆计数器之后的附加组件中记录结果。
  一个组件在一项工作中只有一个职责是很典型的。

在VehicleCounter组件内部，<vehicle, count> map用于将车辆类型计数存储在内存中。当接收到新事件时，它会相应地更新。
在此流式作业中，车辆计数器是计算车辆事件的操作员。该操作符是工作的结束，它不会为下游操作符创建任何输出。

* 连接这些组件.

组装流媒体工作，我们需要添加SensorReader源和VehicleCounter运算符并连接它们。我们为您构建的Job和类中有一些钩子：Stream
Job.addSource()允许您向作业添加数据源。
Stream.applyOperator()允许您将运算符添加到流中。

## 并行化和数据分组

我们将学习一种基本技术来解决大多数分布式系统中的基本挑战。这一挑战是扩展流系统以增加作业的吞吐量，或者换句话说，处理更多数据。

数据并行表示同一任务同时在不同的事件集上执行。
任务并行表示不同的任务同时执行。

数据并行被广泛用于分布式系统中以实现水平扩展。 在这些系统中，通过添加更多计算机来增加并行化会相对容易。 
相反，对于任务并行性，通常需要人工干预将现有流程分解为多个步骤以提高并行性。
流系统是数据并行和任务并行的组合。 在流式系统中，数据并行是指为每个组件创建多个实例，
任务并行是指将整个过程分解为不同的组件来解决问题。 在上一章中，我们应用了任务并行技术并将整个系统分成两个组件。
在本章中，我们将学习如何应用数据并行技术并为每个组件创建多个实例。

我们引入一个 eventDispatcher（事件调度器）的新组件。它将允许我们将数据路由到并行化组件的不同实例。

事件调度器将帮助我们在下游实例之间分配负载。

您唯一需要做的就是在调用applyOperator()函数时添加一个额外的参数，diting 引擎会为您处理其余的。
请记住，流式框架可以帮助您专注于业务逻辑，而不必担心引擎是如何实现的。
不同的引擎可能有不同的方式来应用字段分组。通常，您可能会在不同引擎的名称groupBy()或名称中找到该函数。{operation}ByKey()

错误日志

``
Exception in thread "main" org.apache.commons.lang3.SerializationException: IOException while reading or closing cloned object data
at org.apache.commons.lang3.SerializationUtils.clone(SerializationUtils.java:98)
at cn.zaolunzi.diting.engine.SourceExecutor.<init>(SourceExecutor.java:18)
at cn.zaolunzi.diting.engine.JobStarter.setupComponentExecutors(JobStarter.java:53)
at cn.zaolunzi.diting.engine.JobStarter.start(JobStarter.java:35)
at cn.zaolunzi.diting.job.ParallelizedVehicleCountJob1.main(ParallelizedVehicleCountJob1.java:22)
Caused by: java.io.InvalidClassException: cn.zaolunzi.diting.job.SensorReader; no valid constructor
at java.base/java.io.ObjectStreamClass$ExceptionInfo.newInvalidClassException(ObjectStreamClass.java:170)
at java.base/java.io.ObjectStreamClass.checkDeserialize(ObjectStreamClass.java:917)
at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2203)
at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1712)
at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:519)
at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:477)
at org.apache.commons.lang3.SerializationUtils.clone(SerializationUtils.java:92)
``


这个错误是由于基类没有被序列化
序列化任何子类后，读取数据时它的基类构造函数被触发。
将Serializable的实现添加到父类

大数据生态逐渐变得臃肿

从数据集成、批/流处理、到数据调度，数据从业者都在努力封装复杂度，提供易于操作的方式，简单来说就是配置化、图形化、SQL。