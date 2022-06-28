package br.com.mycapital.hearthstone.demohearthstone.config;

import br.com.mycapital.hearthstone.demohearthstone.controller.CardController;
import br.com.mycapital.hearthstone.demohearthstone.controller.DeckController;
import br.com.mycapital.hearthstone.demohearthstone.exception.BusinessRuleException;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.NotFoundException;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(DeckController.class);
        register(CardController.class);
        register(NotFoundException.class);
        register(BusinessRuleException.class);

    }
}
