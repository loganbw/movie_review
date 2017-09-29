package com.logan.movie_review.controllers;

import com.logan.movie_review.dao.MovieDao;
import com.logan.movie_review.dao.ReviewDao;
import com.logan.movie_review.models.Movie;
import com.logan.movie_review.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private ReviewDao reviewDao;


    @RequestMapping(value = "/")
    public String movieReviewer(Model model) {
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
        return "redirect:/";
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
        return "redirect:/";
    }

    @RequestMapping(value = "/review/{movieId}")
    public String review(Model model,
                         @PathVariable("movieId") long movieId) {
        Movie findMovie = movieDao.findOne(movieId);
        model.addAttribute("reviews", new Review());
        model.addAttribute("movie", findMovie);
        return "review";
    }

    @RequestMapping(value = "/review/{movieId}", method = RequestMethod.POST)
    public String addreview(Model model, @ModelAttribute Review review,
            @PathVariable("movieId")long movieId) {
        Movie movie = movieDao.findOne(movieId);
        review.setMovie(movie);
        reviewDao.save(review);
        return "redirect:/";
    }
}