package cn.weiwei.boot.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

    private static final Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(topics = {"test"})
    public void processMessage(ConsumerRecord<String, String> record) {
        log.debug("topic = {}, partition = {}, offset = {}, key = {}, value = {}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}
