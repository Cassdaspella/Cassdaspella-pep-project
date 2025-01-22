package Controller;

import Service.AccountService;
import Service.MessagesService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Message;
import Model.Account;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    
     AccountService accountService;
     MessagesService messagesService;
    
     public SocialMediaController () {
        this.accountService = new AccountService();
        this.messagesService = new MessagesService();
     }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postUserHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageIDsHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIDHandler);
        app.get("/accounts/{account_id}/messages", this::getMessageByUserHandler);
        
        return app.stop();
    }

    /**
     * Grabs the new account
     * @param context 
     * 
     */
    private void postUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount == null){
            ctx.status(400);
        }
        else {
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        }   
    }

    /**
     * Grabs and makes sure there is a valid login
     */
    private void postLoginHandler(Context ctx) {
//TODO!!!!!! GET THE LOGIN!
    }

    /**
     * grabs the message and makes sure it is valid
     */
    private void postMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messagesService.addMessage(message);
        if (addedMessage == null) { 
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(addedMessage));
            ctx.status(200);
        }
    }

    /**
     * grabs all messages
     * @param ctx
     */
    private void getMessagesHandler(Context ctx) {
        List<Message> messages = messagesService.getMessages();
        ctx.json(messages);
    }

    /**
     * grabs messages by their ID
     * @param ctx
     */
    private void getMessageIDsHandler(Context ctx) {
        //TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    }

    private void deleteMessageByIDHandler(Context ctx) {
        //TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    }
    private void updateMessageByIDHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messagesService.updateMessage(message_id, message);
        System.out.println(updatedMessage);
        if (updatedMessage == null || message.getMessage_text() == "" || message.getMessage_text().length() >= 255) {
            ctx.status(400);            
        }
        else {
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }
    }
    private void getMessageByUserHandler(Context ctx) {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messagesService.getMessagesByUser(account_id);
        ctx.json(messages);
    }
}