package jp.ac.shibaura.it.ie.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
