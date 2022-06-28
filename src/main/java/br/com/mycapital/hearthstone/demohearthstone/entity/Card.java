package br.com.mycapital.hearthstone.demohearthstone.entity;

import br.com.mycapital.hearthstone.demohearthstone.enums.CardType;
import br.com.mycapital.hearthstone.demohearthstone.enums.ClassType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by @talles.reis on 26/06/2022
 */

@Data
@Table(name = "card")
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "power", nullable = false)
    private Integer power;

    @Column(name = "defense", nullable = false)
    private Integer defense;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private CardType type;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
