package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Евгений", "Севостьянов", (byte) 28);
        userService.saveUser("Иван", "Петров", (byte) 31);
        userService.saveUser("Сергей", "Смирнов", (byte) 24);
        userService.saveUser("Владислав", "Иванов", (byte) 22);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

