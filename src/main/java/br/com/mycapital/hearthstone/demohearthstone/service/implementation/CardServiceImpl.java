package br.com.mycapital.hearthstone.demohearthstone.service.implementation;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import br.com.mycapital.hearthstone.demohearthstone.exception.ResourceNotFoundException;
import br.com.mycapital.hearthstone.demohearthstone.repository.CardRepository;
import br.com.mycapital.hearthstone.demohearthstone.service.CardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        super();
        this.cardRepository = cardRepository;
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> saveListOfCards(List<Card> lstCard) {
        List<Card> lstCardSaved = new ArrayList<>();
        for (Card card : lstCard) {
            lstCardSaved.add(cardRepository.save(card));
        }
        return lstCardSaved;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(long id) {
        return cardRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Card", "Id", id));
    }

    @Override
    public Card updateCard(Card card, long id) {
        Card existingCard = cardRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Id", id));

        existingCard.setName(card.getName());
        existingCard.setDescription(card.getDescription());
        existingCard.setPower(card.getPower());
        existingCard.setDefense(card.getDefense());
        existingCard.setType(card.getType());
        existingCard.setClassType(card.getClassType());

        cardRepository.save(existingCard);
        return existingCard;
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Card", "Id", id));
        cardRepository.deleteById(id);
    }
}
