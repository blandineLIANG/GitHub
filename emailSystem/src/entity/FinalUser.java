package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: FinalUser
 *
 */
@Entity
@Table(name="FinalUser")
public class FinalUser implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String passwd;
	private NewGroupRight newGroupRight;
	private Box box;
	
	public FinalUser() {
		super();
	}
   	
	public String getUserRight(){
		return null;
		
	}
	public void updateUserRight(){
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userId")
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId=userId;
	}
	
	
	@Column(name="userName")
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	@Column(name="passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="rightId")
	public NewGroupRight getNewGroupRight() {
		return newGroupRight;
	}

	public void setNewGroupRight(NewGroupRight newGroupRight) {
		this.newGroupRight = newGroupRight;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="boxId")
	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
}
