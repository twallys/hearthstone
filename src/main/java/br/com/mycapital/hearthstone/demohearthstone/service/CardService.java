package br.com.mycapital.hearthstone.demohearthstone.service;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;

import java.util.List;

public interface CardService {
    Card saveCard(Card card);
    List<Card> saveListOfCards(List<Card> lstCard);
    List<Card> getAllCards();
    Card getCardById(long id);
    Card updateCard(Card card, long id);
    void deleteCard(long id);
}
