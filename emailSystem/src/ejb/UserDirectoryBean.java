package ejb;
import java.util.List;

import javax.ejb.*;
import javax.persistence.*;

import entity.*;

@Stateless
public class UserDirectoryBean implements IUserDirectory {
	@PersistenceContext(unitName="dataBase")	
	private EntityManager em;	
	public FinalUser addUser(String login, String passwd) {
		if(checkUserLogin(login)!=null)
			return null;		
		FinalUser user = new FinalUser();
		NewGroupRight newGroupRight = new NewGroupRight();
		newGroupRight.setReadRight(false);
		newGroupRight.setWriteRight(false);
		newGroupRight.setUser(user);
		user.setUserName(login);
		user.setPasswd(passwd);
		user.setNewGroupRight(newGroupRight);
		
		Box box = new Box();
		box.setUser(user);
		user.setBox(box);
		try{
			em.persist(user);
			//result = true;
		}catch(EntityExistsException e){
			System.out.print("this entity exists");
		}	
		return user;
	}
	
	public FinalUser login(String login, String psw){
		Query q = em.createQuery("select u from FinalUser u where u.userName = :userName and u.passwd = :passwd");
	    q.setParameter("userName", login);
	    q.setParameter("passwd", psw);
	    return (FinalUser)q.getSingleResult();			
	}

	public void removeUser(String userName) {
		
		Query q = em.createQuery("select u from FinalUser u where u.userName = :userName");
		q.setParameter("userName", userName);	
		FinalUser user =(FinalUser)q.getSingleResult(); 
		em.remove(user);		
	}

	public List<FinalUser> lookUpAllUsers() {
		
		Query q = em.createQuery("select u from FinalUser u");	
		List<FinalUser> results = q.getResultList();
		return results;
	}

	
	public NewGroupRight lookUpAUserRights(String userName) {
		
		Query q = em.createQuery("select r from NewGroupRight r inner join FinalUser u where u.userName = :userName");
		q.setMaxResults(1);
	    q.setParameter("userName", userName);
		return (NewGroupRight)q.getSingleResult();
	}

	public int updateAUserRights(String writeRight, String readRight, int userId) {
		int i = -1;
		Boolean br=new Boolean(readRight); 
		boolean readRightB=br.booleanValue();
		Boolean bw=new Boolean(writeRight); 
		boolean writeRightB=bw.booleanValue();
		//Query q = em.createQuery("update NewGroupRight r set r.readRight = :readRight, r.writeRight = :writeRight where r.rightId = :rightId");
		Query q = em.createQuery("update NewGroupRight r set r.readRight = :readRight, r.writeRight = :writeRight where r.user.userId = :userId");
	    q.setParameter("readRight", readRightB);
	    q.setParameter("writeRight", writeRightB);
	   // q.setParameter("rightId", rightId);
	    q.setParameter("userId", userId);
    
	    i = q.executeUpdate();
	    return i;
			
	}

	@Override
	public FinalUser checkUserLogin(String login) {
		List<FinalUser> users = null;
		int size = 0;
		Query q = em.createQuery("select u from FinalUser u where u.userName = :userName");
	    q.setParameter("userName", login);
	    users = (List<FinalUser>)q.getResultList(); 
	    size =  users.size();
	    if (size == 0)
	    	return null;
	    else 
	    	return  users.get(0);
	}

	@Override
	public NewGroupRight checkUserRight(int userId) {
		NewGroupRight userRight = null;
		Query q = em.createQuery("select r from NewGroupRight r where r.user.userId = :userId");
		q.setParameter("userId", userId);
		userRight = (NewGroupRight)q.getSingleResult();
		return userRight;
	}
	
	

}
