package ejb;
import java.util.List;

import javax.ejb.*;

import entity.*;

@Remote
public interface IUserDirectory {
	public FinalUser addUser(String login, String passwd);
	public void removeUser(String nameDelete);
	public List<FinalUser> lookUpAllUsers();
	public NewGroupRight lookUpAUserRights(String userName);
	public int updateAUserRights(String writeRight, String readRight, int userId);
	public FinalUser checkUserLogin(String login);
	public FinalUser login(String Login, String psw);
	public NewGroupRight checkUserRight(int userId);
}
