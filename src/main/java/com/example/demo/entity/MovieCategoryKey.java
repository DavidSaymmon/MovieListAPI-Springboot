package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class MovieCategoryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    public MovieCategoryKey(Long categoryId, Long movieId) {
        this.categoryId = categoryId;
        this.movieId = movieId;
    }

    public MovieCategoryKey() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MovieCategoryKey other = (MovieCategoryKey) obj;
        if (movieId == null) {
            if (other.movieId != null) {
                return false;
            }
        } else if (!movieId.equals(other.movieId)) {
            return false;
        }
        if (categoryId == null) {
            if (other.categoryId != null) {
                return false;
            }
        } else if (!categoryId.equals(other.categoryId)) {
            return false;
        }
        return true;
    }
    private Long movieId;
    private Long categoryId;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
