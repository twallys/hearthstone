package br.com.mycapital.hearthstone.demohearthstone.controller;

import br.com.mycapital.hearthstone.demohearthstone.DTO.DeckLazyConsult;
import br.com.mycapital.hearthstone.demohearthstone.entity.Deck;
import br.com.mycapital.hearthstone.demohearthstone.exception.BusinessRuleException;
import br.com.mycapital.hearthstone.demohearthstone.exception.ResourceNotFoundException;
import br.com.mycapital.hearthstone.demohearthstone.service.DeckService;
import org.hibernate.Hibernate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * ON THIS CONTROLLER WAS USED THE JAX RS
 */

@Service
@Path("/api/deck")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        super();
        this.deckService = deckService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public Response saveDeck(Deck deck){
        try {
            deckService.saveDeck(deck);
            return Response.status(Response.Status.CREATED).entity(deck).build();
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List<DeckLazyConsult> getAllDecks(){
        return deckService.getAllDecks();
    }

    @GET
    @Path("/detailed")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List<Deck> getAllDetailedDecks(){
        return deckService.getAllDetailedDecks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getDeckById(@PathParam("id") long deckId){
        try {
            Deck deck = deckService.getDeckByIdDetailed(deckId);
            return Response.status(Response.Status.OK).entity(deck).build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public Response updateDeck(@PathParam("id") long id, Deck deck){
        try {
            Deck deckUpdated = deckService.updateDeck(deck, id);
            return Response.status(Response.Status.OK).entity(deckUpdated).build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDeck(@PathParam("id") long id){
        try {
            deckService.deleteDeck(id);
            return Response.status(Response.Status.OK).entity("Deck deleted successfully!.").build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

}
