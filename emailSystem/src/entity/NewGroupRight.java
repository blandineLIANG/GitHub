package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReadNewGroup
 *
 */
@Entity
@Table(name="NewGroupRight")
public class NewGroupRight implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private int rightId;	
	private FinalUser user;	
	private boolean readRight;	
	private boolean writeRgiht;
	public NewGroupRight() {
		super();
	}
	@Column(name="readRight")
	public boolean getReadRight() {
		return readRight;
	}

	public void setReadRight(boolean readRight) {
		this.readRight = readRight;
	}
	@Column(name="writeRight")
	public boolean getWriteRight() {
		return writeRgiht;
	}

	public void setWriteRight(boolean writeRgiht) {
		this.writeRgiht = writeRgiht;
	}

	/**
	 * @return the rightId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "rightId")
	public int getRightId() {
		return rightId;
	}
	public void setRightId(int rightId) {
		this.rightId = rightId;
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
