package com.bidhub.controller;

import com.bidhub.dto.AuctionItemDTO;
import com.bidhub.service.AuctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<AuctionItemDTO> addItem(@Valid @RequestBody AuctionItemDTO itemDTO) {
        AuctionItemDTO response = auctionService.addItem(itemDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable String id) {
        auctionService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<AuctionItemDTO>> getActiveAuctions() {
        List<AuctionItemDTO> response = auctionService.getActiveAuctions();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/won/{userId}")
    public ResponseEntity<List<AuctionItemDTO>> getWonAuctions(@PathVariable String userId) {
        List<AuctionItemDTO> response = auctionService.getWonAuctions(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{auctionId}/claim")
    public ResponseEntity<Void> claimAuction(@PathVariable String auctionId, @RequestParam String userId) {
        auctionService.claimAuction(auctionId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}