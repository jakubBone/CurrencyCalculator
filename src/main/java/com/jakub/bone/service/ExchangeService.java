package com.jakub.bone.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeService {

    private final Map<String, BigDecimal> currencyRates;

    public ExchangeService() {
        currencyRates = new HashMap<>();
        currencyRates.put("USD", new BigDecimal("4.10"));
        currencyRates.put("EUR", new BigDecimal("4.00"));
        currencyRates.put("PLN", BigDecimal.ONE);
    }

    public BigDecimal exchange(BigDecimal amount, String from, String to){
        if(from.equals(to)){
            return amount;
        }

        BigDecimal fromCurrency = currencyRates.get(from);
        BigDecimal toCurrency = currencyRates.get(to);

        if(fromCurrency == null ||  toCurrency == null){
            throw new IllegalArgumentException("unknown currency:" +
                    (fromCurrency == null ? from: to ));
        }

        BigDecimal plnAmount = amount.multiply(currencyRates.get(from));

        // 2 - wynik zaokrąglony do dwóch miejsc
        // RoundingMode.HALF_UP - zaokrąglenie do góry
        return plnAmount.divide(currencyRates.get(to), 2, RoundingMode.HALF_UP);
    }

}
