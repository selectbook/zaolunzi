package cn.zaolunzi.diting.api;

import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 1:22
 */
public abstract class JoinOperator extends Operator {
    private static final GroupingStrategy DEFAULT_GROUPING = new AllGrouping();
    
    public JoinOperator(String name, int parallelism, Map<String, GroupingStrategy> groupingMap) {
        super(name, parallelism, groupingMap);
    }
    
    /**
     * Get the grouping key of an event by stream name. Fallback to the default grouping strategy
     * if not configured.
     * @return The grouping strategy of this operator
     */
    @Override
    public GroupingStrategy getGroupingStrategy(String streamName) {
        GroupingStrategy grouping = super.getGroupingStrategy(streamName);
        return grouping != null ? grouping : DEFAULT_GROUPING;
    }
}
