package com.jakub.bone.controller;

import com.jakub.bone.domain.model.CurrencyRequest;
import com.jakub.bone.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/currency")
public class ExchangeController {
    // @RestController - informuje Springa, że klasa będzie obsługiwać żądania REST
    // @RequestMapping - ustala wspólny prefiks dla wszystkich endpointów

    ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    // @PostMapping - przyjmuje żądanie HTTP POST
    // Odczytuje dane w formacie JSON (dzięki @RequestBody)
    @PostMapping("/exchange")
    public ResponseEntity<BigDecimal> exchangeCurrency(@RequestBody CurrencyRequest req){
        try{
            BigDecimal result = service.exchange(req.getAmount(), req.getFrom(), req.getTo());
            return ResponseEntity.ok(result);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    // ResponseEntity -reprezentuje pełną odpowiedź HTTP
    // Status HTTP - 200 OK, 400 Bad Request, 404 Not Found
    // Nagłówki (headers) - dodatkowe informacje do odpowiedzi
    // Treść (body) - zawartość odpowiedzi
}
