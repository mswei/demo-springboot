package cn.weiwei.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义再均衡监听器
 */
public class HandleRebalance implements ConsumerRebalanceListener {

    private static final Logger log = LoggerFactory.getLogger(HandleRebalance.class);
    private Consumer<String, String> consumer;
    private Map<TopicPartition, OffsetAndMetadata> offsets;

    public HandleRebalance(Consumer<String, String> consumer, Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.consumer = consumer;
        this.offsets = offsets;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        log.info("Lost partitions in rebalance. Committing current offsets: {}", offsets);
        consumer.commitSync(offsets);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

    }
}
