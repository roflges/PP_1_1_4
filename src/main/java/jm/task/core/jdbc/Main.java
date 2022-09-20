package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();


        userDao.saveUser("Lyalya", "Kata", (byte) 5);
        userDao.saveUser("Po", "Kata", (byte) 6);
        userDao.saveUser("Tinky", "Kata", (byte) 8);
        userDao.saveUser("Winky", "kata", (byte) 7);

        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    }
}
