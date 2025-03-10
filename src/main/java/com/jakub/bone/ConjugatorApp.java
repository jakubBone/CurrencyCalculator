package com.jakub.bone;


import com.jakub.bone.controller.ExchangeController;
import com.jakub.bone.domain.model.CurrencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootApplication
public class ConjugatorApp {
    @Autowired
    private ExchangeController exchangeController;

    public static void main(String[] args) {
        SpringApplication.run(ConjugatorApp.class, args);
    }

    // TESTOWANIE APLIKACJI

    // Rozwiązanie 1:
    /*
    curl -X POST http://localhost:8080/api/currency/exchange
            -H "Content-Type: application/json"
            -d "{
                \"amount\":100,
                \"from\":\"EUR\",
                \"to\":\"PLN\"
                }"
     */

    // Rozwiązanie 2:
    @Override
    public void run(String... args) throws Exception {
        // Przykładwy request
        CurrencyRequest req = new CurrencyRequest();
        req.setAmount(new BigDecimal("100"));
        req.setFrom("EUR");
        req.setTo("PLN");

        // Wywołaj metodę kontrolera bezpośrednio
        ResponseEntity<?> response = exchangeController.exchangeCurrency(req);
        System.out.println("Wynik wymiany: " + response.getBody());
    }
}
