package com.logan.movie_review.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="movies")
public class Movie {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String genre;
    private String imbdlink;
    private String releasedate;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImbdlink() {
        return imbdlink;
    }

    public void setImbdlink(String imbdLink) {
        this.imbdlink = imbdLink;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releaseDate) {
        this.releasedate = releaseDate;
    }
}
