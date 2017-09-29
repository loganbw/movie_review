package com.logan.movie_review.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
    private long id;
    private  String nameofuser;
    private String movietitle;
    private int rating;
    private int age;
    private String gender;
    private String occupation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameofuser() {
        return nameofuser;
    }

    public void setNameofuser(String nameofuser) {
        this.nameofuser = nameofuser;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
