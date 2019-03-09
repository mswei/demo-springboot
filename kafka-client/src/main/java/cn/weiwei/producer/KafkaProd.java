package cn.weiwei.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProd {

    private Producer<String, String> producer;

    public KafkaProd() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "192.168.0.200:9092");
        // kafkaProps.put("partitioner.class", "cn.weiwei.producer.BananaPartitioner");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(kafkaProps);
    }

    public void sendMessage() {
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "weiwei", "233333");

        try {
            producer.send(record, new ProducerCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
