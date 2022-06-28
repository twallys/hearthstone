package br.com.mycapital.hearthstone.demohearthstone.service;

import br.com.mycapital.hearthstone.demohearthstone.DTO.DeckLazyConsult;
import br.com.mycapital.hearthstone.demohearthstone.entity.Deck;

import java.util.List;

public interface DeckService {
    Deck saveDeck(Deck deck);
    List<DeckLazyConsult> getAllDecks();
    List<Deck> getAllDetailedDecks();
    Deck getDeckById(long id);
    Deck getDeckByIdDetailed(long id);
    Deck updateDeck(Deck deck, long id);
    void deleteDeck(long id);
}
