package br.com.mycapital.hearthstone.demohearthstone.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "deck_card")
public class DeckCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "id_deck")
    private Deck deck;
}
