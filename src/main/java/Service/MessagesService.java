package Service;

import java.util.List;

import DAO.MessagesDAO;
import Model.Message;

public class MessagesService {
    public MessagesDAO messagesDAO;

    public MessagesService(){
        messagesDAO = new MessagesDAO();
    }

    public MessagesService(MessagesDAO messagesDAO){
        this.messagesDAO = messagesDAO;
    }
    /**
     * x : Make sure that these messages are made through an existing User
     * o : Make sure that the message_text is NOT blank and LESS THAN 255
     * x : Otherwise, return null
     */
    public Message addMessage(Message message){
        if(message.getMessage_text() == "" || message.getMessage_text() == null || message.getMessage_text().length() > 255 ){
        return null;
        }
        else {
            return messagesDAO.insertMessage(message);
        }
    }
/**
 * o : return all messages
 * @return
 */
    public List<Message> getMessages(){
        return messagesDAO.getAllMessages();
    }

    public Message updateMessage(int message_id, Message message){
        if (messagesDAO.getMessageById(message_id) != null) {
            messagesDAO.udpdateMessageById(message_id, message);
            return messagesDAO.getMessageById(message_id);
        }
        else{
            return null;
        }
    }

    public Message getMessageById(int id){
        if (messagesDAO.getMessageById(id) != null) {
            return messagesDAO.getMessageById(id);
        } else {
            return null;
        }
    }
}
