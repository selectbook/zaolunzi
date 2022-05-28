# diting (谛听)

streaming system(谛听 流计算系统)

流式系统是大数据领域中最热门的话题，
选择一个好的流式系统开源项目，并逐步深入研究，是掌握流式系统的核心法门。

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

