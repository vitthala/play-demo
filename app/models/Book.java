package models;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "demo@cassandra_pu")
public class Book {
    @Id
    private int id;

    @Column(name="author")
    private int author;

    @Column(name="name")
    private String name;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setIntId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
