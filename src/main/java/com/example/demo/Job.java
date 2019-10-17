package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postedDate;

    @NotNull
    @Size(min=4, max=30)
    private String title;

    @NotNull
    @Size(min=5,max=100)
    private String description;

    @NotNull
    @Size(min=3, max=20)
    private String author;

    @NotNull
    @Size(min=10,max=14)
    private String phone;

    @ManyToOne
    private User user;

    public Job() {
    }

    public Job(Date postedDate,
               @NotNull @Size(min = 4, max = 30) String title,
               @NotNull @Size(min = 5, max = 100) String description,
               @NotNull @Size(min = 3, max = 20) String author,
               @NotNull @Size(min = 10, max = 14) String phone) {
        this.postedDate = postedDate;
        this.title = title;
        this.description = description;
        this.author = author;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
