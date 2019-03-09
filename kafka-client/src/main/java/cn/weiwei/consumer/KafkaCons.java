package cn.weiwei.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class KafkaCons {

    private static final Logger log = LoggerFactory.getLogger(KafkaCons.class);
    private Consumer<String, String> consumer;

    public KafkaCons() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "192.168.0.200:9092");
        kafkaProps.put("group.id", "testGroup01");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(kafkaProps);
    }

    public void processMessage() {
        consumer.subscribe(Collections.singletonList("test"));

        try {
            // 轮询
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    log.debug("topic = {}, partition = {}, offset = {}, key = {}, value = {}\n",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());

                    // do something
                }
            }
        } finally {
            consumer.close();
        }
    }
}
