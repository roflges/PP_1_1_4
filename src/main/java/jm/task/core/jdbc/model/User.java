package jm.task.core.jdbc.model;

import javax.persistence.*;
/*
 аннотации JPA
 @Entity - Указывает, что класс является сущностью,позволяет Java-объектам вашего класса быть связанными с БД.
 @Table - Задает первичную таблицу для аннотированной сущности.
 @id - Определяет первичный ключ сущности.
 @GeneratedValue - Обеспечивает спецификацию стратегий генерации значений первичных ключей.
 @column - Указывает сопоставленный столбец для постоянного свойства или поля.
*/
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//саморазвитие первичного ключа. Когда база данных вставляет данные, она автоматически присваивает значение первичному ключу

    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}