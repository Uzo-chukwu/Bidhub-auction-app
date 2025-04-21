package com.bidhub.controller;

import com.bidhub.dto.CreateAuctionItemRequest;
import com.bidhub.model.AuctionItem;
import com.bidhub.service.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/create")
    public ResponseEntity<AuctionItem> createItem(@RequestBody CreateAuctionItemRequest request) {
        return ResponseEntity.ok(auctionService.createItem(request));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AuctionItem>> getActive() {
        return ResponseEntity.ok(auctionService.getActiveAuctions());
    }
}

