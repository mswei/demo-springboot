package cn.weiwei.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// @Component
public class KafkaCons {

    private static final Logger log = LoggerFactory.getLogger(KafkaCons.class);
    private Consumer<String, String> consumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets; // 当前偏移量

    public KafkaCons() {
        Properties kafkaProps = new Properties();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.200:9092");
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup01");
        kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // 关闭自动提交偏移量（默认自动5秒提交一次）
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(kafkaProps);
        currentOffsets = new HashMap<>();
    }

    public void processMessage() {
        consumer.subscribe(Collections.singletonList("test"), new HandleRebalance(consumer, currentOffsets));

        try {
            // 轮询
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5 * 1000)); // 轮询阻塞时间（毫秒）
                log.debug("轮询中......");

                for (ConsumerRecord<String, String> record : records) {
                    // 遍历列表，逐条处理Kafka传递的数据
                    log.debug("topic = {}, partition = {}, offset = {}, key = {}, value = {}",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());

                    // Do something

                    // 循环中，记录偏移量
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                }

                // 处理当前批次消息结束，异步提交偏移量（也可以挪到循环中，在特定时间点提交）
                consumer.commitAsync(currentOffsets, new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception != null) {
                            log.error("Commit failed for offsets {}", offsets); // 提交失败则记录日志
                        }
                    }
                });
            }
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
        } finally {
            try {
                consumer.commitSync(); // 同步提交（和异步相组合）偏移量，消费者关闭时提交偏移量
            } finally {
                consumer.close();
                log.info("Closed consumer and we are done.");
            }
        }
    }

}
