//DAO (data access object) — один из наиболее распространенных паттернов проектирования, "Доступ к данным". Его смысл прост — создать в приложении слой, который отвечает только за доступ к данным

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;//Интерфейс org.hibernate.Session является мостом между приложением и Hibernate

import javax.transaction.Transactional;
import java.util.List;

@Transactional //Указывает, что свойство или поле не являются постоянными.
public class UserDaoHibernateImpl implements UserDao {

    private final Session session = Util.getSessionFactory().openSession();//соединение с бд, рабоатет с объектами

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try {
            session.beginTransaction();//проверит, есть ли уже существующая транзакция, если да, то не создаст новую транзакцию
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NULL,`lastName` VARCHAR(45)" +
                    " NULL,`age` VARCHAR(45) NULL, PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)").executeUpdate();//транзакция.фиксация();
            session.getTransaction().commit();//возвращаемый результат;
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {


        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));//создать
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session.beginTransaction();
            session.delete(session.get(User.class,id));//удалить
            session.createQuery("DELETE User WHERE id = :id");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List users = null;

        try {
            users = session.createQuery("FROM User").list();
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getStackTrace();
            session.close();
        }
    }
}