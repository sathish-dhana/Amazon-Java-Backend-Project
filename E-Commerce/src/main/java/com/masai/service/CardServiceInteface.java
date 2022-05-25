package com.masai.service;

import com.masai.beans.Card;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;

public interface CardServiceInteface {
	
	public Card addCard(Card card);
	
	public String removeCard(Card card);
	
	public Card updateCard(Card card, Card updatedCard);
	
}
