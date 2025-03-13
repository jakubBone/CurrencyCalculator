package com.jakub.bone;


import com.jakub.bone.controller.ExchangeController;
import com.jakub.bone.data.Datasource;
import com.jakub.bone.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ExchangeApp   {
    // @SpringBootApplication - uruchamia i konfiguruje

    // Autowired - Wstrzykuje instancje (bez tworzenia new obj)
    @Autowired
    private ExchangeController exchangeController;


    public static void main(String[] args) {

        SpringApplication.run(ExchangeApp.class, args);
    }

    // Test
    /*
    curl -X POST http://localhost:8080/api/currency/exchange
            -H "Content-Type: application/json"
            -d "{
                \"amount\":100,
                \"from\":\"EUR\",
                \"to\":\"PLN\"
                }"
     */
}
