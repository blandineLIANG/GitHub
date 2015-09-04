package ejb;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.persistence.*;

import entity.*;

@Stateless
public class MailBoxManagerBean implements IMailBoxManager {
	
	@PersistenceContext(unitName="dataBase")
	private EntityManager em;
	@Override
	public List<Message> readAUserNewMessages(int userId) {
		Query q = em.createQuery("select m from Message m inner join FinalUser u on m.receiverName = u.userName where m.alreadyRead='false' and u.userId = :userId");
		q.setParameter("userId", userId);
		List<Message> msgs = (List<Message>) q.getResultList();
		for(Message m: msgs){
			m.setAlreadyRead(true);	
			em.persist(m);
		}
		
		return msgs;

	}
	@Override
	public List<Message> readAUserAllMessages(int userId) {
		Query q = em.createQuery("select m from Message m inner join FinalUser u on m.receiverName = u.userName and u.userId = :userId");
		q.setParameter("userId", userId);
		List<Message> msg = (List<Message>) q.getResultList();
		return msg;
	}
	
	@Override
	public boolean deleteAUserMessage(int msgId) {
		int numOfEntity = -1;
		Query q = em.createQuery("delete from Message  where messageId=:msgId");
		q.setParameter("msgId", msgId);
		numOfEntity = q.executeUpdate();	
		if(numOfEntity > 0)
			return true;
		return false;
	}
	@Override
	public int deleteAUserReadMessages(int userId) {
		int numOfEntity = -1;
		Query q = em.createQuery("delete m from Message m, FinalUser u where m.alreadyRead = true and u.userId = :userId");
		q.setParameter("userId", userId);
		numOfEntity = q.executeUpdate();	
        return numOfEntity;	
	}
	@Override
	public void sendAMessageToABox(String senderName,String receiverName,String subject,String body) {
		System.out.print(subject);
		Message msg = new Message();
		msg.setAlreadyRead(false);
		msg.setSenderName(senderName);
		msg.setReceiverName(receiverName);
		msg.setSubject(subject);
		msg.setBody(body);		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");     
		String date = sDateFormat.format(new java.util.Date());
		msg.setSendingDate(date);
	
		Query q = em.createQuery("select b from Box b inner join FinalUser u on b.user.userId = u.userId where u.userName = :receiverName");
		q.setParameter("receiverName", receiverName);	
		List<Message> msgs=null;
		Box box = (Box) q.getSingleResult();
		msg.setBox(box);
		if(box.getMessages()!=null)
			msgs = box.getMessages();
		else
			msgs = new ArrayList<Message>();
		msgs.add(msg);		
		box.setMessages(msgs);		
		em.persist(box);
		em.persist(msg);
	}
	
	@Override
	public void sendNews(String senderName,String subject,String body) {
		News n = new News();	
		n.setSenderName(senderName);
		n.setSubject(subject);
		n.setBody(body);
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");     
		String date = sDateFormat.format(new java.util.Date());
		n.setSendingDate(date);		
		em.persist(n);	
	}

	@Override
	public List<News> readNews() {
		Query q = em.createQuery("select news from News news");		
		List<News> news = (List<News>) q.getResultList();
		return news;
	}


}
