package com.zecchinel.bitbudget.config;

import com.zecchinel.bitbudget.service.AwsSecretsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Value("${database.source}")
    private String databaseSource;

    @Bean
    public DataSource dataSource() {
        AwsSecretsManager secret;

        if (databaseSource.equals("rds")) {
            secret = AwsSecretsManager.getSecret();
        } else {
            secret = AwsSecretsManager.getLocalhostSecret();
        }

        return DataSourceBuilder.create()
                .url("jdbc:postgresql://" + secret.getHost() + ":" + secret.getPort() + "/" + secret.getDbInstanceIdentifier())
                .username(secret.getUsername())
                .password(secret.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
