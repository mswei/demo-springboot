package cn.weiwei.demo.controller;

import cn.weiwei.demo.configuration.ConfigBean;
import cn.weiwei.demo.configuration.ConfigDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class, ConfigDB.class})
public class LucyController {

    private final ConfigBean config;
    private final ConfigDB configDB;

    @Autowired
    public LucyController(@Qualifier("appConfig") ConfigBean config, ConfigDB configDB) {
        this.config = config;
        this.configDB = configDB;
    }

    @RequestMapping("/lucy")
    public String lucy() {
        return config.getGreeting() + ".  " + config.getName() + " ☆ " + config.getAge() + " ☆ " +
                config.getUuid() + " ☆ " + config.getNumber() + " ☆ " + config.getMax() + " ☆ " +
                config.getValue();
    }

    @RequestMapping("/data")
    public String data() {
        return configDB.getDriver() + " : " + configDB.getUrl();
    }
}
