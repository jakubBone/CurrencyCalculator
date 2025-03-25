package com.jakub.bone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Component scanning and auto-configuration for the Spring Boot app
public class CurrencyExchangeApp {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApp.class, args);
    }

    /*
    Endpoint test:

    # Windows CMD:
    curl -X POST "http://localhost:8080/api/currency/exchange" -H "Content-Type: application/json" -d "{\"amount\":100,\"from\":\"EUR\",\"to\":\"PLN\"}"

    # PowerShell:
    curl -X POST http://localhost:8080/api/currency/exchange -H "Content-Type: application/json" -d '{ "amount":100, "from":"EUR", "to":"PLN" }'
     */
}
