package client;


import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.*;
import entity.*;


public class UserClient {
	public static void main(String args[]) {
		InitialContext ic;
		try {
			// initialize context.
			ic = new InitialContext();
			// initialize services.
			IMailBoxManager mbManager = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
			IUserDirectory userDirectory = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
			// initialize a final user
			FinalUser currentUser = null;		
			System.out.println("------- Log in | New user ----------------------");
			System.out.println("------- Please input 0 or 1 --------------------");
			System.out.println("------- 0 : Log in -----------------------------");
			System.out.println("------- 1 : New user ---------------------------");
			String choice = input();			
			if( choice.equals("0") ){			
				System.out.println("------- Please enter UserName and Password ");
				String userName = input(); 
				String psw = input(); 
				try{
					currentUser = userDirectory.login(userName,psw);
					System.out.println(currentUser);
				}
				catch(Exception e) {
					System.out.println("------- Wrong pasword or User doesn't exist");
				}
			}
			else{			
				do{
				System.out.println("------- Please enter UserName and Password pour new user ");
				String login = input();
				String passwd = input();
				currentUser = userDirectory.addUser(login, passwd);
				if(currentUser == null) 
					 System.out.println("this user name has been used");					
				}while(currentUser == null);
				System.out.println(currentUser);
			}
			
			boolean i = true; 
			while(i){
				System.out.println("---------You have these options---------------------" );
				System.out.println("------- 1 : Send email ");
				System.out.println("------- 2 : Unread emails ");
				System.out.println("------- 3 : All emails ");
				System.out.println("------- 4 : Delete an email ");
				System.out.println("------- 5 : Send news ");
				System.out.println("------- 6 : Unread news ");	
				System.out.println("------- 7 : Exit ");	
				String choiceMailBox = input();
				switch(choiceMailBox){
					case "1":
						System.out.println("------- Please input the receiver's name");
						String receiverName = input();
						System.out.println("------- Please input the subject");
						String subject = input();
						System.out.println("------- Please input the body text");
						String body = input();
						mbManager.sendAMessageToABox(currentUser.getUserName(),receiverName,subject,body);						
						break;
					case "2":
						List<Message> unreadMsg = mbManager.readAUserNewMessages(currentUser.getUserId());
						if (unreadMsg != null){
							for(Message m: unreadMsg){
								System.out.println("SenderName   |" + m.getSenderName());
								System.out.println("ReceiverName |" + m.getReceiverName());
								System.out.println("Date         |" + m.getSendingDate());
								System.out.println("Subject      |" + m.getSubject());
								System.out.println("Body Text    |" + m.getBody());	
								System.out.println("----------------------------------------" );	
							}
						}
						else 
							System.out.println("There is no new messages");
						break;				
					case "3":
						List<Message> allMsgs = mbManager.readAUserAllMessages(currentUser.getUserId());
						for(Message m: allMsgs){
							System.out.println("SenderName   |" + m.getSenderName());
							System.out.println("ReceiverName |" + m.getReceiverName());
							System.out.println("Date         |" + m.getSendingDate());
							System.out.println("Subject      |" + m.getSubject());
							System.out.println("Body Text    |" + m.getBody());	
							System.out.println("----------------------------------------" );	
						}
						break;
					case "4":
						List<Message> allMs = mbManager.readAUserAllMessages(currentUser.getUserId());
						for(Message m: allMs){
							System.out.println("Message id      |" + m.getMessageId());
							System.out.println("SenderName   |" + m.getSenderName());
							System.out.println("ReceiverName |" + m.getReceiverName());
							System.out.println("Date         |" + m.getSendingDate());
							System.out.println("Subject      |" + m.getSubject());					
							System.out.println("----------------------------------------" );
						}
							System.out.println("----please input the id of message that you want to delete------" );
							String idMessage = input();
							int idM=Integer.parseInt(idMessage);
							boolean result = mbManager.deleteAUserMessage(idM);							
							break;						
					case "5":
						NewGroupRight right = userDirectory.checkUserRight(currentUser.getUserId());
						if (right.getWriteRight() == true){ 
							System.out.println("------- Please input the subject");
							String subjectNews = input();
							System.out.println("------- Please input the body text");
							String bodyNews = input();						
							mbManager.sendNews(currentUser.getUserName(), subjectNews, bodyNews);
						}
						else 
							System.out.println("Sorry, you do not have the right to send news");
						break;
					case "6":
						NewGroupRight right0 = userDirectory.checkUserRight(currentUser.getUserId());
						if (right0.getReadRight() == true){ 							
							List<News> news = mbManager.readNews();
							for(News n: news){
								System.out.println("SenderName   |" + n.getSenderName());
								System.out.println("Date         |" + n.getSendingDate());
								System.out.println("Subject      |" + n.getSubject());
								System.out.println("Body Text    |" + n.getBody());	
								System.out.println("----------------------------------------" );	
							}
						}
						else
							System.out.println("Sorry, you do not have the right to read news");
						break;
					case "7":
						i = false;						
				}
				
			}								
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}

private static String input() {

	Scanner in = new Scanner(System.in);
	String get = in.next();
	
	return get;
}

}