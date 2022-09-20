package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl user = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
        user.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        user.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        user.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        user.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return user.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        user.cleanUsersTable();
    }




}
