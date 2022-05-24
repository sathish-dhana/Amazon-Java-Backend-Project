package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Card;
import com.masai.beans.Seller;
import com.masai.exception.CardAlreadyExistException;
import com.masai.exception.CardDetailsNotFoundException;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.repository.CardCrudRepo;

@Service
public class CardService implements CardServiceInteface {
	
	@Autowired
	private CardCrudRepo cardCrudRepo;	
	
	@Override
	public Card addCard(Card card) {
		Optional<Card> checkCard = cardCrudRepo.findByCardNumber(card.getCardNumber());
		Card savedcard = null;
		
		if (!checkCard.isPresent()) {
			savedcard = cardCrudRepo.save(card);
		} else {
			throw new CardAlreadyExistException("Card details exist");
		}
		
		return savedcard;
	}

	@Override
	public String removeCard(Card card) {
		Optional<Card> checkSeller = cardCrudRepo.findByCardNumber(card.getCardNumber());
		
		if (checkSeller.isPresent()) {
			cardCrudRepo.delete(card);
			return "Card number " + card.getCardNumber() + " removed succesfully";
		} else {
			throw new CardDetailsNotFoundException("Card number " + card.getCardNumber() + " not Found");
		}
	}
	
	@Override
	public Card updateCard(Card card, Card updatedCard) {
		Optional<Card> checkSeller = cardCrudRepo.findByCardNumber(card.getCardNumber());
		
		if (checkSeller.isPresent()) {
			cardCrudRepo.delete(card);
			Card savedCard = cardCrudRepo.save(updatedCard);
			return savedCard;
		} else {
			throw new CardDetailsNotFoundException("Card number " + card.getCardNumber() + " not Found");
		}
	}
	

}
