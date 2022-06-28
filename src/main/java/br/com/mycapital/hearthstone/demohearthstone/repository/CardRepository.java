package br.com.mycapital.hearthstone.demohearthstone.repository;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
