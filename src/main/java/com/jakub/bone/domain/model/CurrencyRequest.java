package com.jakub.bone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequest {
    private BigDecimal amount;
    private String from;
    private String to;

    // @NoArgsConstructor - Jackson wymaga bezargumentowgo, aby stworzyć nowy obiekt
}


