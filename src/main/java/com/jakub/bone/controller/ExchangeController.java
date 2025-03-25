package com.jakub.bone.controller;

import com.jakub.bone.data.Datasource;
import com.jakub.bone.domain.model.CurrencyRequest;
import com.jakub.bone.repository.ExchangeRepository;
import com.jakub.bone.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;

@RestController // Informs Spring that this class will handle REST requests and return JSON responses
@RequestMapping("api/currency") // Sets a common prefix for all endpoints in this controller
public class ExchangeController {
    private final ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    /*
     # Handles HTTP POST request for '/api/currency/exchange'
     # Accepts input in JSON format (via @RequestBody).
     # 'req CurrencyRequest' containing amount, source, and target currency
      */
    @PostMapping("/exchange")
    public ResponseEntity<BigDecimal> exchangeCurrency(@RequestBody CurrencyRequest req){
        // @RequestBody maps the incoming JSON request body to a CurrencyRequest object
        try{
            BigDecimal result = service.exchange(req.getAmount(), req.getFrom(), req.getTo());
            Datasource datasource = new Datasource();
            ExchangeRepository repository = new ExchangeRepository(datasource);
            repository.createTable();
            repository.saveExchange(result, req.getFrom(), req.getTo());
            return ResponseEntity.ok(result);

        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    ResponseEntity represents the full HTTP response:

    # Status: e.g. 200 OK, 400 Bad Request, 404 Not Found
    # Headers: additional response metadata
    # Body: actual response content (e.g. result of conversion)
     */

}
