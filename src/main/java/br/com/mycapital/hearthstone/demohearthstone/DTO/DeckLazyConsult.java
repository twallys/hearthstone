package br.com.mycapital.hearthstone.demohearthstone.DTO;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Simula uma tabela no banco de dados
 *
 * Created by @talles.reis on 27/06/2022
 */

@Data
public class DeckLazyConsult {
    private long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
