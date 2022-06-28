package br.com.mycapital.hearthstone.demohearthstone.controller;

import br.com.mycapital.hearthstone.demohearthstone.entity.Card;
import br.com.mycapital.hearthstone.demohearthstone.exception.BusinessRuleException;
import br.com.mycapital.hearthstone.demohearthstone.exception.ResourceNotFoundException;
import br.com.mycapital.hearthstone.demohearthstone.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * ON THIS CONTROLLER WAS USED THE SPRING FRAMEWORK ANNOTATION
 */

@Service
@Path("/api/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        super();
        this.cardService = cardService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public Response saveCard(Card card){
        try {
            cardService.saveCard(card);
            return Response.status(Response.Status.CREATED).entity(card).build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("save-list")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public Response saveListOfCards(List<Card> lstCard){
        try {
            cardService.saveListOfCards(lstCard);
            return Response.status(Response.Status.CREATED).entity(lstCard).build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getCause()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getCardById(@PathParam("id") long cardId){
        try {
            Card card = cardService.getCardById(cardId);
            return Response.status(Response.Status.OK).entity(card).build();
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
    public Response updateCard(@PathParam("id") long id, Card card){
        try {
            Card cardUpdated = cardService.updateCard(card, id);
            return Response.status(Response.Status.OK).entity(cardUpdated).build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCard(@PathParam("id") long id){
        try {
            cardService.deleteCard(id);
            return Response.status(Response.Status.OK).entity("Card deleted successfully!.").build();
        } catch (BusinessRuleException | ResourceNotFoundException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

}
