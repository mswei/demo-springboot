package cn.weiwei.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

@Component
public class KafkaCons {

    private static final Logger log = LoggerFactory.getLogger(KafkaCons.class);
    private Consumer<String, String> consumer;

    public KafkaCons() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "192.168.0.200:9092");
        kafkaProps.put("group.id", "testGroup01");
        kafkaProps.put("enable.auto.commit", false); // 关闭自动提交偏移量
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(kafkaProps);
    }

    public void processMessage() {
        consumer.subscribe(Collections.singletonList("test"));

        try {
            // 轮询
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000)); // 轮询阻塞时间（毫秒）
                for (ConsumerRecord<String, String> record : records) {
                    // 遍历列表，逐条处理Kafka传递的数据
                    log.debug("topic = {}, partition = {}, offset = {}, key = {}, value = {}\n",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    // do something
                }

                consumer.commitAsync(new OffsetCommitCallback() { // 异步提交偏移量
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
                consumer.commitSync(); // 同步提交（和异步相组合）偏移量
            } finally {
                consumer.close();
            }
        }
    }
}
