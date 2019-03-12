package cn.weiwei.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Timer;
import java.util.TimerTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProdTest {

    @Autowired
    private KafkaProd kafkaProd;

    @Test
    public void testSend() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                kafkaProd.sendMessage(); // 3秒发送一次消息
            }
        }, 0, 3 * 1000);

        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
    }
}
