package com.example.configdemo.controller;

import com.example.configdemo.config.DemoConfig.EchoService;
import com.example.configdemo.config.props.DemoProps;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoProps props;
    private final Environment env;
    private final ObjectProvider<EchoService> echoProvider; // 可能不存在，用 Provider 更安全

    public DemoController(DemoProps props, Environment env, ObjectProvider<EchoService> echoProvider) {
        this.props = props;
        this.env = env;
        this.echoProvider = echoProvider;
    }

    /** 查看当前生效环境与数据源 URL（验证多环境配置） */
    @GetMapping("/env")
    public Map<String, Object> env() {
        Map<String, Object> map = new HashMap<>();
        map.put("activeProfiles", env.getActiveProfiles());
        map.put("datasource.url", env.getProperty("spring.datasource.url"));
        return map;
    }

    /** 查看配置绑定效果（验证 @ConfigurationProperties） */
    @GetMapping("/props")
    public DemoProps props() {
        return props;
    }

    /** 测试条件装配（验证 @ConditionalOnProperty） */
    @GetMapping("/echo")
    public String echo(@RequestParam(defaultValue = "hello") String q) {
        EchoService svc = echoProvider.getIfAvailable();
        return (svc == null) ? "EchoService not enabled" : svc.echo(q);
    }
}
