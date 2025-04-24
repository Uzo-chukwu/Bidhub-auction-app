package com.bidhub.service;

import com.bidhub.dto.BidDTO;
import com.bidhub.dto.BidRequestDTO;

import java.util.List;

public interface BidService {
    BidDTO placeBid(BidRequestDTO bidRequestDTO, String userId);
    List<BidDTO> getUserBids(String userId);
    List<BidDTO> getBidsForItem(String auctionItemId);
}