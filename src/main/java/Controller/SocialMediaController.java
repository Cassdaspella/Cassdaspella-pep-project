package Controller;

import Model.Account;
import Service.AccountService;
import Service.MessagesService;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

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
        app.get("/accounts/{account_id}", this::getMessageByUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context 
     * 
     */
    private void postUserHandler(Context ctx) {
    }
    private void postLoginHandler(Context ctx) {
    }
    private void postMessagesHandler(Context ctx) {
    }
    private void getMessagesHandler(Context ctx) {
    }
    private void getMessageIDsHandler(Context ctx) {
    }
    private void deleteMessageByIDHandler(Context ctx) {
    }
    private void updateMessageByIDHandler(Context ctx) {
    }
    private void getMessageByUserHandler(Context ctx) {
    }
}