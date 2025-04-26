package com.bidhub.service.impl;

import com.bidhub.dto.AuctionItemDTO;
import com.bidhub.model.AuctionItem;
import com.bidhub.model.AuctionStatus;
import com.bidhub.repository.AuctionItemRepository;
import com.bidhub.service.AuctionService;
import com.bidhub.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionItemRepository auctionItemRepository;
    private final UserProfileService userProfileService;

    @Autowired
    public AuctionServiceImpl(AuctionItemRepository auctionItemRepository, UserProfileService userProfileService) {
        this.auctionItemRepository = auctionItemRepository;
        this.userProfileService = userProfileService;
    }

    @Override
    public AuctionItemDTO addItem(AuctionItemDTO itemDTO) {
        AuctionItem item = new AuctionItem();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setStartingBid(itemDTO.getStartingBid());
        item.setCurrentHighestBid(itemDTO.getStartingBid());
        item.setStartTime(LocalDateTime.now());
        item.setEndTime(LocalDateTime.now().plusHours(24));
        item.setStatus(AuctionStatus.ACTIVE);

        AuctionItem savedItem = auctionItemRepository.save(item);
        return mapToAuctionItemDTO(savedItem);
    }

    @Override
    public void removeItem(String id) {
        if (!auctionItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Auction item not found");
        }
        auctionItemRepository.deleteById(id);
    }

    @Override
    public List<AuctionItemDTO> getActiveAuctions() {
        return auctionItemRepository.findByStatus(AuctionStatus.ACTIVE)
                .stream()
                .map(this::mapToAuctionItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuctionItemDTO> getWonAuctions(String userId) {
        return auctionItemRepository.findByHighestBidderIdAndStatus(userId, AuctionStatus.ENDED)
                .stream()
                .map(this::mapToAuctionItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void claimAuction(String auctionId, String userId) {
        AuctionItem item = auctionItemRepository.findById(auctionId)
                .orElseThrow(() -> new IllegalArgumentException("Auction item not found"));

        if (!item.getStatus().equals(AuctionStatus.ENDED)) {
            throw new IllegalArgumentException("Auction is not ended");
        }
        if (!item.getHighestBidderId().equals(userId)) {
            throw new IllegalArgumentException("User is not the winner");
        }
        if (!userProfileService.isProfileComplete(userId)) {
            throw new IllegalArgumentException("Complete your profile to claim the auction");
        }

        item.setStatus(AuctionStatus.PENDING_PAYMENT);
        auctionItemRepository.save(item);
    }

    private AuctionItemDTO mapToAuctionItemDTO(AuctionItem item) {
        AuctionItemDTO dto = new AuctionItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setStartingBid(item.getStartingBid());
        dto.setCurrentHighestBid(item.getCurrentHighestBid());
        dto.setHighestBidderId(item.getHighestBidderId());
        dto.setStartTime(item.getStartTime());
        dto.setEndTime(item.getEndTime());
        dto.setStatus(item.getStatus());
        return dto;
    }
}