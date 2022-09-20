package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mybdtest";
    private static final String NAME = "root";
    private static final String PASS = "admin";
    private static Util util;


    public Util () {}


    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, NAME, PASS);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}