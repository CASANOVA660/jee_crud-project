package dao;
import java.util.List;
import BEANS.user;

public interface userinterface {
	public void addUser(user user);
    public boolean updateUser(user user);
    public boolean deleteUser(int id);
    public user getUserById(int id);
    public List<user> getAllUsers();
	

}
