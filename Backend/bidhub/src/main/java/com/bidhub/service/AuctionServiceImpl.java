package com.bidhub.service;

import com.bidhub.dto.CreateAuctionItemRequest;
import com.bidhub.model.AuctionItem;
import com.bidhub.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionItemRepository auctionRepo;


    @Override
    public AuctionItem createItem(CreateAuctionItemRequest request) {
        AuctionItem item = new AuctionItem();
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setStartingPrice(request.getStartingPrice());
        item.setHighestBid(request.getStartingPrice());
        item.setStartTime(LocalDateTime.now());
        item.setEndTime(LocalDateTime.now().plusHours(24));
        return auctionRepo.save(item);
    }

    @Override
    public List<AuctionItem> getActiveAuctions() {
        return auctionRepo.findBySoldFalse();
    }

    @Override
    public void markItemAsPaid(String auctionItemId) {
        AuctionItem item = auctionRepo.findById(auctionItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setPaid(true);
        auctionRepo.save(item);
    }

}

