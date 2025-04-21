package com.bidhub.service;

import com.bidhub.dto.PlaceBidRequest;
import com.bidhub.model.AuctionItem;
import com.bidhub.model.Bid;
import com.bidhub.repository.AuctionItemRepository;
import com.bidhub.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepo;
    private final AuctionItemRepository auctionRepo;


    @Override
    public Bid placeBid(PlaceBidRequest request) {
        AuctionItem item = auctionRepo.findById(request.getAuctionItemId())
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        if (LocalDateTime.now().isAfter(item.getEndTime())) {
            throw new RuntimeException("Auction has ended");
        }

        if (request.getAmount() <= item.getHighestBid()) {
            throw new RuntimeException("Bid must be higher than current highest");
        }

        item.setHighestBid(request.getAmount());
        item.setHighestBidderId(request.getUserId());
        auctionRepo.save(item);

        Bid bid = new Bid();
        bid.setAuctionItemId(request.getAuctionItemId());
        bid.setUserId(request.getUserId());
        bid.setAmount(request.getAmount());
        bid.setTimestamp(LocalDateTime.now());
        return bidRepo.save(bid);
    }

    @Override
    public List<Bid> getBidsForAuction(String auctionItemId) {
        return bidRepo.findByAuctionItemId(auctionItemId);
    }

    @Override
    public List<Bid> getBidsByUser(String userId) {
        return bidRepo.findByUserId(userId);
    }

}
