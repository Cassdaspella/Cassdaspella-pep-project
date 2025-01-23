package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * TODO: 
 * Create messsages || Delete messages w/ message ID || Update Message ||
 * Retrieve: All messages || All messages for User || Messages by Message Id || 
 */
public class MessagesDAO {
    /**
     * Creating a message!
     * @param message users will be able to create a new message!
     * @return null
     */
    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(
                    generated_message_id, 
                    message.getPosted_by(), 
                    message.getMessage_text(), 
                    message.getTime_posted_epoch());
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return null;
    }
/**
 * Get all messages!
 * @return a List<> of messages!
 */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                rs.getString("message_text"),  rs.getLong("time_posted_epoch"));
                
                messages.add(message);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
/**
 * getting messages by their IDs
 * @param id users will input the ID of the message they are looking for
 * @return the Message object by the message_id
 */
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                    rs.getString("message_text"),  rs.getLong("time_posted_epoch"));
                    return message;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
/**
 * delete the Message object by the ID it was assigned to
 * @param id users will input their id to find the message and then delete it
 */
    public void deleteMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "DELETE FROM message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * Update the message ID. Users must input a message id to find a certain message
 * @param id users will input their id to find a certain message they want to update/alter
 * @param message the new message they want to input
 */
    public void udpdateMessageById(int id, Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
/**
 * Using a right join to join both tables to find messages from a username
 * Should return a list of messages and return nothing if the user has not sent a message.
 * @param username users will input a username that will find all the messages from that user
 * @return a List<> of messages 
 */
    public List<Message> getMessagesByUser(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account RIGHT JOIN message ON account.account_id = message.posted_by WHERE account.account_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));

            messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return messages;
    }
}
