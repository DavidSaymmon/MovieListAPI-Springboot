    package com.example.demo.entity;

    import java.time.LocalDateTime;

    import jakarta.persistence.Column;
    import jakarta.persistence.EmbeddedId;
    import jakarta.persistence.Entity;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.MapsId;
    import jakarta.persistence.PrePersist;
    import jakarta.persistence.PreUpdate;

    @Entity
    public class MovieCategory {

        @EmbeddedId
        MovieCategoryKey id;

        @ManyToOne
        @MapsId("categoryId")
        @JoinColumn(name = "category_id", nullable = false)
        private Category category;

        @ManyToOne
        @MapsId("movieId")
        @JoinColumn(name = "movie_id", nullable = false)
        private Movie movie;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        @PrePersist()
        public void onCreate() {
            LocalDateTime now = LocalDateTime.now();
            createdAt = now;
            updatedAt = now;
        }

        @PreUpdate
        public void onUpdate() {
            updatedAt = LocalDateTime.now();
        }

        public MovieCategory() {
        }

        public MovieCategory(Movie movie, Category category) {
            this.movie = movie;
            this.category = category;
            this.id = new MovieCategoryKey(category.getId(), movie.getId());
        }

        public MovieCategoryKey getId() {
            return id;
        }

        public Category getCategory() {
            return category;
        }

        public Movie getMovie() {
            return movie;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MovieCategory other = (MovieCategory) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
    }