package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: message
 *
 */
@Entity
@Table(name="Message")
public class Message implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int messageId;
	private String senderName;
	private String receiverName;
	private String sendingDate;
	private String subject;
	private String body;
	private boolean alreadyRead; 
	
	private Box box;
	
	public Message(){
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "messageId")
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	@Column(name="senderName")
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	@Column(name="receiverName")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	@Column(name="sendingDate")
	public String getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name="body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	@Column(name="alreadyRead")
	public boolean isAlreadyRead() {
		return alreadyRead;
	}

	public void setAlreadyRead(boolean alreadyRead) {
		this.alreadyRead = alreadyRead;
	}
	
	public void readMessage(){
		alreadyRead=true;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name="boxId")
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	
}
