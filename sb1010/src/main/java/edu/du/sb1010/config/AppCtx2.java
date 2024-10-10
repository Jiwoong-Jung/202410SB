package edu.du.sb1010.config;

import edu.du.sb1010.spring.Client;
import edu.du.sb1010.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx2 {
    @Bean
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setHost("host");
//        client.afterPropertiesSet();
        return client;
    }

    @Bean(destroyMethod = "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        client.connect();
        return client;
    }
}
