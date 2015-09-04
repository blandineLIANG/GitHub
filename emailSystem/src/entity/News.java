package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="News")


public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int newsId;
	private String senderName;
	private String sendingDate;
	private String subject;
	private String body; 
	
	public News () {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "newsId")
	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	@Column(name="senderName")
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
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

   
}


