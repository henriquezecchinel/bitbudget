package com.zecchinel.bitbudget.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.google.gson.Gson;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwsSecretsManager {
    private String username;
    private String password;
    private String engine;
    private String host;
    private int port;
    private String dbInstanceIdentifier;

    private static Gson gson = new Gson();

    public static AwsSecretsManager getSecret() {
        String secretName = "bitbudget-development-db";
        Region region = Region.of("eu-south-1");

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e;
        }

        var secretString = getSecretValueResponse.secretString();
        return gson.fromJson(secretString, AwsSecretsManager.class);
    }

    public static AwsSecretsManager getLocalhostSecret() {
        var localhostSecretString = "{"
                + "\"username\": \"postgres\","
                + "\"password\": \"postgres\","
                + "\"engine\": \"postgres\","
                + "\"host\": \"localhost\","
                + "\"port\": 5432,"
                + "\"dbInstanceIdentifier\": \"bitbudget-local\""
                + "}";

        return gson.fromJson(localhostSecretString, AwsSecretsManager.class);
    }

}