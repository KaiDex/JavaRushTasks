package userdatabase.service;

import userdatabase.model.User;

import java.util.List;

/**
 * Created by 100500 on 06.07.2017.
 */
public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers();

    public List<User> getUsersByName(String name);

}
