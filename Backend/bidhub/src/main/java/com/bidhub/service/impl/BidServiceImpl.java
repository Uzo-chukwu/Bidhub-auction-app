package com.bidhub.service.impl;

import com.bidhub.dto.BidDTO;
import com.bidhub.dto.BidRequestDTO;
import com.bidhub.model.AuctionItem;
import com.bidhub.model.AuctionStatus;
import com.bidhub.model.Bid;
import com.bidhub.repository.AuctionItemRepository;
import com.bidhub.repository.BidRepository;
import com.bidhub.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final AuctionItemRepository auctionItemRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, AuctionItemRepository auctionItemRepository) {
        this.bidRepository = bidRepository;
        this.auctionItemRepository = auctionItemRepository;
    }

    @Override
    public BidDTO placeBid(BidRequestDTO bidRequestDTO, String userId) {
        AuctionItem item = auctionItemRepository.findById(bidRequestDTO.getAuctionItemId())
                .orElseThrow(() -> new IllegalArgumentException("Auction item not found"));

        if (!item.getStatus().equals(AuctionStatus.ACTIVE)) {
            throw new IllegalArgumentException("Auction is not active");
        }
        if (bidRequestDTO.getBidAmount() <= item.getCurrentHighestBid()) {
            throw new IllegalArgumentException("Bid must be higher than current highest bid");
        }

        Bid bid = new Bid();
        bid.setAuctionItemId(bidRequestDTO.getAuctionItemId());
        bid.setUserId(userId);
        bid.setBidAmount(bidRequestDTO.getBidAmount());
        bid.setBidTime(LocalDateTime.now());

        item.setCurrentHighestBid(bidRequestDTO.getBidAmount());
        item.setHighestBidderId(userId);
        auctionItemRepository.save(item);

        Bid savedBid = bidRepository.save(bid);
        return mapToBidDTO(savedBid);
    }

    @Override
    public List<BidDTO> getUserBids(String userId) {
        return bidRepository.findByUserId(userId)
                .stream()
                .map(this::mapToBidDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BidDTO> getBidsForItem(String auctionItemId) {
        return bidRepository.findByAuctionItemId(auctionItemId)
                .stream()
                .map(this::mapToBidDTO)
                .collect(Collectors.toList());
    }

    private BidDTO mapToBidDTO(Bid bid) {
        BidDTO dto = new BidDTO();
        dto.setId(bid.getId());
        dto.setAuctionItemId(bid.getAuctionItemId());
        dto.setUserId(bid.getUserId());
        dto.setBidAmount(bid.getBidAmount());
        dto.setBidTime(bid.getBidTime());
        return dto;
    }
}