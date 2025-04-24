package com.bidhub.service;

import com.bidhub.dto.AuctionItemDTO;

import java.util.List;

public interface AuctionService {
    AuctionItemDTO addItem(AuctionItemDTO itemDTO);
    void removeItem(String id);
    List<AuctionItemDTO> getActiveAuctions();
    List<AuctionItemDTO> getWonAuctions(String userId);
    void claimAuction(String auctionId, String userId);
}