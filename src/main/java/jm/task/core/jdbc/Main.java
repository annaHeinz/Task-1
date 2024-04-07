package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static UserService us = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        В методе main класса Main должны происходить следующие операции:
//        Создание таблицы User(ов)
//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//        Очистка таблицы User(ов)
//        Удаление таблицы
        us.createUsersTable();
        us.saveUser("Петр", "Петров", (byte) 20);//добавляем в бд
        us.saveUser("Виктор", "Петров", (byte) 30);
        us.saveUser("Петр", "Максимов", (byte) 22);
        us.saveUser("Петр", "Петров", (byte) 21);
        for (User u : us.getAllUsers()) {
            System.out.println(u);
        }
//        us.cleanUsersTable();
//        us.dropUsersTable();

    }
}
