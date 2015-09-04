package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Box")
public class Box implements Serializable{
	private int boxId;
	private FinalUser user;
	private List<Message> messages;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "boxId")
	public int getBoxId() {
		return boxId;
	}
	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Box")
	@JoinColumn(name="messageId")
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	public FinalUser getUser() {
		return user;
	}
	public void setUser(FinalUser user) {
		this.user = user;
	}
}
