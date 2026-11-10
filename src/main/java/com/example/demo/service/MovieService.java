package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Movie.MovieCreateDTO;
import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.dto.Movie.MovieUpdateDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Movie;
import com.example.demo.entity.MovieCategory;
import com.example.demo.infra.MovieMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public MovieResponseDTO createMovie(MovieCreateDTO request) {
        Movie movie = MovieMapper.convertRequestToMovie(request);
        movie.setName(movie.getName());
        Movie savedMovie = movieRepository.save(movie);
        if(request.categoryIds() != null)
            savedMovie.updateCategories(getRequestCategories(savedMovie, request.categoryIds()));
        return MovieMapper.convertMovieToResponse(movieRepository.save(savedMovie));
    }
    @Transactional(readOnly=true)
    public Optional<MovieResponseDTO> getById(Long Id) {
        return movieRepository.findById(Id)
                .map(movie -> MovieMapper.convertMovieToResponse(movie));
    }

    @Transactional(readOnly=true)
    public List<MovieResponseDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> MovieMapper.convertMovieToResponse(movie)).toList();
    }
    @Transactional
    public Optional<MovieResponseDTO> updateMovie(Long id, MovieUpdateDTO request) {
        Optional<Movie> movieOPT = movieRepository.findById(id);
        if (movieOPT.isEmpty())
            return Optional.empty();

        Movie movie = movieOPT.get();
        if (request.categoryIds() != null) movie.updateCategories(getRequestCategories(movie, request.categoryIds()));
        if (request.name() != null && !request.name().isBlank()) movie.setName(request.name());
        if (request.language() != null && !request.language().isBlank()) movie.setLanguage(request.language());
        if (request.duration() != null) movie.setDuration(request.duration());
        if (request.releaseDate() != null) movie.setReleaseDate(request.releaseDate());

        return Optional.of(MovieMapper.convertMovieToResponse(movieRepository.save(movie)));
    }
    @Transactional
    public boolean deleteById(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }
        movieRepository.deleteById(id);
        return true;
    }


    @Transactional(readOnly=true)
    private Set<MovieCategory> getRequestCategories(Movie movie, Set<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds.stream().toList());
        if (categories.size() != categoryIds.size()) {
            throw new IllegalArgumentException("one or more categories were not found");
        }
        return categories.stream()
                .map(category -> new MovieCategory(movie, category))
                .collect(Collectors.toSet());
    }
}
