package br.com.mycapital.hearthstone.demohearthstone.controller;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import br.com.mycapital.hearthstone.demohearthstone.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ON THIS CONTROLLER WAS USED THE SPRING FRAMEWORK ANNOTATION
 */

@RestController
@RequestMapping("/api/card")
public class CardControllerSpringAnnotation {

    private CardService cardService;

    public CardControllerSpringAnnotation(CardService cardService) {
        super();
        this.cardService = cardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> saveCard(@RequestBody Card card){
        return new ResponseEntity<Card>(cardService.saveCard(card), HttpStatus.CREATED);
    }

    @PostMapping(value = "save-list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> saveListOfCards(@RequestBody List<Card> lstCard){
        return new ResponseEntity<List<Card>>(cardService.saveListOfCards(lstCard), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getCardById(@PathVariable("id") long cardId){
        return new ResponseEntity<Card>(cardService.getCardById(cardId), HttpStatus.OK);
    }

    @PutMapping(value= "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> updateCard(@PathVariable("id") long id
            ,@RequestBody Card card){
        return new ResponseEntity<Card>(cardService.updateCard(card, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") long id){
        cardService.deleteCard(id);
        return new ResponseEntity<String>("Card deleted successfully!.", HttpStatus.OK);
    }

}
