package cn.weiwei.producer;

import cn.weiwei.producer.domain.Customer;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 自定义序列化器，不推荐；可使用JSON、Avro、Thrift、Protobuf等现有的序列化器
 */
public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Customer data) {
        try {
            byte[] serializedName;
            int stringSize;

            if (data == null) {
                return null;
            } else {
                if (data.getCustomerName() != null) {
                    serializedName = data.getCustomerName().getBytes(StandardCharsets.UTF_8);
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(data.getCustomerID());
            buffer.putInt(stringSize);
            buffer.put(serializedName);

            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Customer to byte[] " + e);
        }
    }

    @Override
    public void close() {

    }
}
