package com.example.configdemo.config;

import com.example.configdemo.config.props.DemoProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoProps.class)
public class DemoConfig {

    /** 只有当 feature.echo.enabled=true 时，才装配 EchoService */
    @Bean
    @ConditionalOnProperty(prefix = "feature.echo", name = "enabled", havingValue = "true")
    public EchoService echoService() {
        return new EchoService();
    }

    /** 一个非常简单的服务，用来证明条件装配生效 */
    public static class EchoService {
        public String echo(String in) { return "[echo] " + in; }
    }
}
