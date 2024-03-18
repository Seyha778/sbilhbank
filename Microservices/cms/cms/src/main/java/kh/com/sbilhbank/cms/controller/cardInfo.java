package kh.com.sbilhbank.cms.controller;

import kh.com.sbilhbank.cms.model.card;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/card-accounts")
public class cardInfo {
    private final List<card> cards = new ArrayList<>();

    // Pre-populate some fixed accounts for testing
    {
        card card1 = new card("123456", "Account Holder 1");
        card card2 = new card("789012", "Account Holder 2");

        cards.add(card1);
        cards.add(card2);
    }

    @Operation(summary = "Get card details by card number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Card not found")
    })
    @GetMapping("/{cardNumber}")
    public ResponseEntity<card> getAccount(@PathVariable String cardNumber) {

       card card = cards.get(Integer.parseInt(cardNumber));

        if (card != null) {
            return ResponseEntity.ok(card);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}