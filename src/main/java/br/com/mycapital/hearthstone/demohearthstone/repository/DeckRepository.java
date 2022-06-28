package br.com.mycapital.hearthstone.demohearthstone.repository;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import br.com.mycapital.hearthstone.demohearthstone.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

    /**
     * TO BE USED IN CASES THAT WE ARE OUT A TRANSACTION BUT STILL NEED THE CARDS
     * @param id
     * @return
     */
    @Query("SELECT u FROM Deck AS u JOIN FETCH u.cards WHERE u.id=:id")
    Deck getDeckByIdDetailed(@Param("id") Long id);

    @Query("SELECT u FROM Deck AS u JOIN FETCH u.cards")
    List<Deck> getAllDetailedDecks();
}
