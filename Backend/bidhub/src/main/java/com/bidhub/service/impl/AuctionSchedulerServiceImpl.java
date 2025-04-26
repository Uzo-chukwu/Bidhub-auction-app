package com.bidhub.service.impl;

import com.bidhub.model.AuctionItem;
import com.bidhub.model.AuctionStatus;
import com.bidhub.repository.AuctionItemRepository;
import com.bidhub.service.AuctionSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuctionSchedulerServiceImpl implements AuctionSchedulerService {
    private final AuctionItemRepository auctionItemRepository;

    @Autowired
    public AuctionSchedulerServiceImpl(AuctionItemRepository auctionItemRepository) {
        this.auctionItemRepository = auctionItemRepository;
    }

    @Override
    @Scheduled(fixedRate = 60000) // Run every minute
    public void checkAuctionStatus() {
        LocalDateTime now = LocalDateTime.now();

        // End active auctions past their end time
        for (AuctionItem auctionItem : auctionItemRepository.findByStatus(AuctionStatus.ACTIVE)) {
            if (auctionItem.getEndTime().isBefore(now)) {
                auctionItem.setStatus(AuctionStatus.ENDED);
                auctionItemRepository.save(auctionItem);
            }
        }

        // Relist unpaid auctions after 24 hours
        auctionItemRepository.findByStatus(AuctionStatus.PENDING_PAYMENT)
                .stream()
                .filter(item -> item.getEndTime().plusHours(24).isBefore(now))
                .forEach(item -> {
                    item.setStatus(AuctionStatus.RELISTED);
                    item.setCurrentHighestBid(item.getStartingBid());
                    item.setHighestBidderId(null);
                    item.setStartTime(now);
                    item.setEndTime(now.plusHours(24));
                    auctionItemRepository.save(item);
                });
    }
}