package com.jakub.bone.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeService {

    public enum Currency {
        PLN, USD, EUR
    }

    private final Map<Currency, BigDecimal> currencyRates;

    public ExchangeService() {
        currencyRates = new HashMap<>()
        currencyRates.put(Currency.USD, new BigDecimal("4.10"));
        currencyRates.put(Currency.EUR, new BigDecimal("4.00"));
        currencyRates.put(Currency.PLN, BigDecimal.ONE);
    }

    public BigDecimal exchange(BigDecimal amount, Currency from, Currency to){
        if(from == to){
            return amount;
        }

        BigDecimal fromCurrency = currencyRates.get(from);
        BigDecimal toCurrency = currencyRates.get(to);

        if(fromCurrency == null ||  toCurrency == null){
            throw new IllegalArgumentException("unknown currency:" +
                    (fromCurrency == null ? from: to ));
        }
         // 4 euro
        BigDecimal plnAmount = amount.multiply(currencyRates.get(from));

        // 16 pln
        return plnAmount.divide(currencyRates.get(from), RoundingMode.HALF_UP);

    }
}
