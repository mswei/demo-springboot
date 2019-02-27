package config;

import feign.Retryer;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 覆盖默认的FeignClient配置
 *
 * @see FeignClientsConfiguration
 */
@Configuration
public class FeignConfig {

    /**
     * 覆盖默认请求失败重试策略 Retryer.NEVER_RETRY
     *
     * @return 请求失败重试策略，重试问隔为 100 毫秒，最大重试时间为 1 秒，重试次数为 5 次。
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5);
    }
}
