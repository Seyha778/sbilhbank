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

    @Operation(summary = "Get account details by account number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{cardNumber}")
    public ResponseEntity<card> getAccount(@PathVariable String cardNumber) {
//        if (cardNumber == null) {
//            return ResponseEntity.badRequest().build();
//        }

        card account = cards.stream()
                .filter(a -> cardNumber.equals(a.getCardNumber()))
                .findFirst()
                .orElse(null);

        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}