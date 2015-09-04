package client;

import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.IMailBoxManager;
import ejb.IUserDirectory;
import entity.FinalUser;
import entity.Message;
import entity.NewGroupRight;

public class SuperUserClient {
	public static void main(String args[]) {
		InitialContext ic;
		try {
			// initialize context.
			ic = new InitialContext();
			// initialize services.
			FinalUser currentUser = null;
			IMailBoxManager mbManager = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
			IUserDirectory userDirectory = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
			System.out.println("------- Super user Log in ---------------------------------");
			System.out.println("------- Please input super user name -----------");
			String userName = input();
			System.out.println("------- Please input password ------------------");
			String psw = input();
			//check super user's login
			if(userName.equals("root") && psw.equals("root")){			
				boolean i = true;
				while(i){
					System.out.println("------- 1 : look all users ");
					System.out.println("------- 2 : modifier the right of a user ");
					System.out.println("------- 3 : delete a user ");
					System.out.println("------- 4 : exit");
					String choiceSuperUser = input();				
					switch(choiceSuperUser){
					case "1":
						List<FinalUser> users = userDirectory.lookUpAllUsers();
						for(FinalUser u: users){
							System.out.println("User Name   |" + u.getUserName());
							System.out.println("Write Right |" + u.getNewGroupRight().getWriteRight());
							System.out.println("Read Right  |" + u.getNewGroupRight().getReadRight());						
							System.out.println("----------------------------------------" );	
						}
						
						break;
					case "2":
						System.out.println("please input the name of this user");
						String nameClient = input();
						NewGroupRight modifiedUserRight = userDirectory.lookUpAUserRights(nameClient);
						System.out.println("----- Write right: "+ modifiedUserRight.getWriteRight());
						System.out.println("----- Read right : "+ modifiedUserRight.getReadRight());						
						System.out.println("please input the read right for this user");
						String readRight = input();
						System.out.println("please input the read right for this user");
						String writeRight = input();
						int userId=userDirectory.checkUserLogin(nameClient).getUserId();						
						userDirectory.updateAUserRights(writeRight, readRight, userId);		
					
						break;
					case "3":
						System.out.println("please input the name of this user");
						String nameDelete = input();
						userDirectory.removeUser(nameDelete);
					case "4":					
						i = false;
					}
					
				}
			}
			else
				System.out.println("------- Wrong pasword or User doesn't exist");		
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		
	
}

private static String input() {

	Scanner in=new Scanner(System.in);
	String get=in.next();
	return get;
}

}