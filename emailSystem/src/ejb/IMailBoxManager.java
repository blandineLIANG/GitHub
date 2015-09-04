package ejb;


import java.util.List;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface IMailBoxManager {
	public List<Message> readAUserNewMessages(int userId);
	public List<Message> readAUserAllMessages(int userId);
	public boolean deleteAUserMessage(int msgId);
	public int deleteAUserReadMessages(int userId);
	public void sendAMessageToABox(String senderName,String receiverName,String subject,String body);
	public void sendNews(String senderName,String subject,String body);
	public List<News> readNews();

}
