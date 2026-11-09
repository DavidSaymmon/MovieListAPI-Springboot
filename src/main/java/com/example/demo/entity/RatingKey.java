package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RatingKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingKey that = (RatingKey) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(movieId, that.movieId);
    }

    public RatingKey() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }

    public RatingKey(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
