package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getUtil().getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {

        try (Statement statement = Util.getUtil().getStatement()) {
            connection.setAutoCommit(false);
            String sql = """
                        CREATE TABLE IF NOT EXISTS users (
                            id INT AUTO_INCREMENT NOT NULL, 
                            name VARCHAR(45) NOT NULL, 
                            lastname VARCHAR(45) NOT NULL, 
                            age INTEGER NOT NULL, PRIMARY KEY(id)) 
                    """;
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            connection.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {

        try (Statement statement = Util.getUtil().getStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            connection.setAutoCommit(true);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void removeUserById(long id) {

        try (Statement statement = Util.getUtil().getStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM users WHERE id = " + String.valueOf(id));
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getUtil().getStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {

        try (Statement statement = Util.getUtil().getStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("TRUNCATE TABLE users");
            connection.commit();
        } catch (SQLException e) {

            connection.setAutoCommit(true);
            connection.rollback();
            e.printStackTrace();
            connection.rollback();
        }
    }
}

