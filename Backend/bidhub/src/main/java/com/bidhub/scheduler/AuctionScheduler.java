package com.bidhub.scheduler;

import com.bidhub.model.AuctionItem;
import com.bidhub.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AuctionScheduler {
    private final AuctionItemRepository auctionRepo;


    @Scheduled(fixedRate = 60 * 1000)
    public void checkExpiredAuctions() {
        List<AuctionItem> auctions = auctionRepo.findBySoldFalse();
        for (AuctionItem item : auctions) {
            if (LocalDateTime.now().isAfter(item.getEndTime()) && !item.isPaid()) {
                item.setHighestBid(0);
                item.setHighestBidderId(null);
                item.setStartTime(LocalDateTime.now());
                item.setEndTime(LocalDateTime.now().plusHours(24));
                auctionRepo.save(item);
            } else if (LocalDateTime.now().isAfter(item.getEndTime())) {
                item.setSold(true);
                auctionRepo.save(item);
            }
        }
    }
}

