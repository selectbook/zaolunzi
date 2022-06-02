package cn.zaolunzi.diting.api;

import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 1:26
 */
public class NamedStreams {
    private final Map<Stream, String> namedStreams;
    
    private NamedStreams(Map<Stream, String> namedStreams) {
        this.namedStreams = namedStreams;
    }
    
    public static NamedStreams of(Map<Stream, String> namedStreams) {
        return new NamedStreams(namedStreams);
    }
    
    /**
     * Apply the operator to the streams. This object is used by Join operator only.
     * @param operator the join operator applied to the named streams.
     * @return the outgoing stream of the operator.
     */
    public Stream join(JoinOperator operator) {
        for (Map.Entry<Stream, String> namedStream: namedStreams.entrySet()) {
            String streamName = namedStream.getValue();
            GroupingStrategy grouping = operator.getGroupingStrategy(streamName);
            namedStream.getKey().applyOperator(operator, streamName);
        }
        return operator.getOutgoingStream();
    }
}
