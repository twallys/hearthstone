package br.com.mycapital.hearthstone.demohearthstone.service.implementation;

import br.com.mycapital.hearthstone.demohearthstone.DTO.DeckLazyConsult;
import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import br.com.mycapital.hearthstone.demohearthstone.entity.Deck;
import br.com.mycapital.hearthstone.demohearthstone.exception.BusinessRuleException;
import br.com.mycapital.hearthstone.demohearthstone.exception.ResourceNotFoundException;
import br.com.mycapital.hearthstone.demohearthstone.repository.DeckRepository;
import br.com.mycapital.hearthstone.demohearthstone.service.CardService;
import br.com.mycapital.hearthstone.demohearthstone.service.DeckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl implements DeckService {
    private DeckRepository deckRepository;
    private CardService cardService;

    public DeckServiceImpl(DeckRepository deckRepository, CardService cardService) {
        super();
        this.deckRepository = deckRepository;
        this.cardService = cardService;
    }

    @Override
    public Deck saveDeck(Deck deck) {
        Map<Card, Long> elementCountMap = deck.getCards().stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));

        List<Card> lstMoreThanTwoCard = elementCountMap.entrySet()
                .stream()
                .filter(map -> map.getValue() > 2)
                .map(map -> map.getKey())
                .collect(Collectors.toList());

        if(!lstMoreThanTwoCard.isEmpty()){
            throw new BusinessRuleException("Deck", "There is more than two identical cards in this deck");
        }

        /**
         * THIS VALIDATION IS PROBABLY UNNECESSARY.
         * BUT IT WAS KEPT HERE BECAUSE IT IS A POSTMAN USE.
         * IN REAL CASES THE USER WILL BE SEND A LIST OF CARDS, WICH WAS CHOSEN
         * IN A SELECT BOX PROVIDED BY BACKEND
         */
        for (Card card: elementCountMap.keySet()) {
            cardService.getCardById(card.getId());
        }

        return deckRepository.save(deck);
    }

    @Override
    public List<DeckLazyConsult> getAllDecks() {

        List<Deck> lstDeckFound = deckRepository.findAll();
        List<DeckLazyConsult> lstDeckLazy = new ArrayList<>();
        for (Deck deck : lstDeckFound) {
            DeckLazyConsult deckLazyConsult = new DeckLazyConsult();
            deckLazyConsult.setId(deck.getId());
            deckLazyConsult.setName(deck.getName());
            deckLazyConsult.setCreatedAt(deck.getCreatedAt());
            deckLazyConsult.setUpdatedAt(deck.getUpdatedAt());

            lstDeckLazy.add(deckLazyConsult);
        }
        return lstDeckLazy;
    }

    @Override
    public List<Deck> getAllDetailedDecks() {
        return deckRepository.getAllDetailedDecks();
    }

    @Override
    public Deck getDeckById(long id) {
        return deckRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Deck", "Id", id));
    }

    @Override
    public Deck getDeckByIdDetailed(long id) {
        Deck deckFound = deckRepository.getDeckByIdDetailed(id);
        if(Objects.nonNull(deckFound)){
            return deckFound;
        }else{
            throw new ResourceNotFoundException("Deck", "Id", id);
        }

    }

    @Override
    public Deck updateDeck(Deck deck, long id) {
        Deck existingDeck = deckRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Deck", "Id", id));

        existingDeck.setName(deck.getName());
        existingDeck.setCards(deck.getCards());

        deckRepository.save(existingDeck);
        return existingDeck;
    }

    @Override
    public void deleteDeck(long id) {
        deckRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Deck", "Id", id));
        deckRepository.deleteById(id);
    }
}
