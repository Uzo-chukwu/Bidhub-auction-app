package com.bidhub.controller;

import com.bidhub.dto.PlaceBidRequest;
import com.bidhub.model.Bid;
import com.bidhub.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bids")
public class BidController {
    private final BidService bidService;

    @PostMapping("/place")
    public ResponseEntity<Bid> placeBid(@RequestBody PlaceBidRequest request) {
        return ResponseEntity.ok(bidService.placeBid(request));
    }

    @GetMapping("/auction/{auctionItemId}")
    public ResponseEntity<List<Bid>> getBids(@PathVariable String auctionItemId) {
        return ResponseEntity.ok(bidService.getBidsForAuction(auctionItemId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bid>> getUserBids(@PathVariable String userId) {
        return ResponseEntity.ok(bidService.getBidsByUser(userId));
    }

}
