package com.zecchinel.bitbudget.config;

import com.zecchinel.bitbudget.service.AwsSecretsManager;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        AwsSecretsManager secret = AwsSecretsManager.getSecret();
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://" + secret.getHost() + ":" + secret.getPort() + "/bitbudget")
                .username(secret.getUsername())
                .password(secret.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
