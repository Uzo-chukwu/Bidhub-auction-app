package com.bidhub.controller;

import com.bidhub.dto.BidDTO;
import com.bidhub.dto.BidRequestDTO;
import com.bidhub.service.BidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {
    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<BidDTO> placeBid(@Valid @RequestBody BidRequestDTO bidRequestDTO, @RequestParam String userId) {
        BidDTO response = bidService.placeBid(bidRequestDTO, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BidDTO>> getUserBids(@PathVariable String userId) {
        List<BidDTO> response = bidService.getUserBids(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/auction/{auctionItemId}")
    public ResponseEntity<List<BidDTO>> getBidsForItem(@PathVariable String auctionItemId) {
        List<BidDTO> response = bidService.getBidsForItem(auctionItemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}