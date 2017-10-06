package com.logan.movie_review.controllers;

import com.logan.movie_review.dao.MovieDao;
import com.logan.movie_review.dao.ReviewDao;
import com.logan.movie_review.dao.UserDao;
import com.logan.movie_review.models.Movie;
import com.logan.movie_review.models.Review;
import com.logan.movie_review.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MovieController {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/home")
    public String movieReviewer(Model model, Principal principal) {
        User me = userDao.findByUsername(principal.getName());
        model.addAttribute("movies", movieDao.findAll());
        return "list";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute Movie newMovie) {
        movieDao.save(newMovie);
        return "redirect:/home";
    }

    @RequestMapping(value = "/info/{movieId}")
    public String info(Model model,
                       @PathVariable("movieId") long movieId) {
        Movie findMovie = movieDao.findOne(movieId);
        model.addAttribute("movie", findMovie);
        return "info";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute Movie updateMovie) {
        movieDao.save(updateMovie);
        return "redirect:/home";
    }

    @RequestMapping(value = "/review/{movieId}")
    public String review(Model model,
                         Principal principal,
                         @PathVariable("movieId") long movieId) {
        User me = userDao.findByUsername(principal.getName());
        Movie findMovie = movieDao.findOne(movieId);
        model.addAttribute("reviews", new Review());
        model.addAttribute("movie", findMovie);
        return "review";
    }

    @RequestMapping(value = "/review/{movieId}", method = RequestMethod.POST)
    public String addreview(Model model, @ModelAttribute Review review,
                            @RequestParam("rating") int rating,
            Principal principal,
            @PathVariable("movieId")long movieId) {
        User me = userDao.findByUsername(principal.getName());
        for (Review reviews : me.getReviews()) {
            if (reviews.getMovie().getId() == movieId && review.getUser() == me) {
                return "redirect:/reviews/" + movieId;
            }
        }
        Review newReview = new Review();
        Movie movie = movieDao.findOne(movieId);
        newReview.setUser(me);
        newReview.setMovie(movie);
        newReview.setNameofuser(me.getUsername());
        newReview.setRating(rating);
        reviewDao.save(newReview);
        return "redirect:/home";
        }

}