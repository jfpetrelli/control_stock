package logic;
import entities.Users;
import data.DataUsers;


public class UsersLogic {
	
	private DataUsers dataUsers;

	public UsersLogic() {
		dataUsers = new DataUsers();
	}
	
	public void create(Users user) {
		dataUsers.create(user);
	}

	public void edit() {
		
	}
	
	public void view() {
		
	}
	
	public void delete() {
		
	}
}
