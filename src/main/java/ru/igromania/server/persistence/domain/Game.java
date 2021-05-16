package ru.igromania.server.persistence.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GAME")
@AttributeOverride(name = "id", column = @Column(name = "GAME_ID"))
public class Game extends LongIdEntity {

    @Column(name = "ORIGINAL_NAME", nullable = false, length = 250)
    private String originalName;

    @Column(name = "RELEASE_DATE", length = 30)
    private String releaseDate;

    @Column(name = "SERIES", length = 100)
    private String gamesSeries;

    @Column(name = "DESCRIPTION", length = 20000)
    private String description;

    @Column(name = "IMAGE_LINK", length = 1000)
    private String imageLink;

    @Column(name = "LINK", nullable = false, length = 100)
    private String Link;

    @Column(name = "RATING", nullable = false)
    private Integer metacriticRating; // Рейтинг метакритика

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "GAME_DEVELOPER",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "DEVELOPER_ID")
    )
    private Set<Developer> developers = new HashSet<>();  // Разработчики


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "GAME_PLATFORM",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLATFORM_ID")
    )
    private Set<Platform> platforms = new HashSet<>(); // Платформы

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "GAME_GENRE",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID")
    )
    private Set<Genre> genres = new HashSet<>(); // Жанры


    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGamesSeries() {
        return gamesSeries;
    }

    public void setGamesSeries(String gamesSeries) {
        this.gamesSeries = gamesSeries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Integer getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(Integer metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Game{" +
                "originalName='" + originalName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", gamesSeries='" + gamesSeries + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", Link='" + Link + '\'' +
                ", metacriticRating=" + metacriticRating +
                ", developers=" + developers +
                ", platforms=" + platforms +
                ", genres=" + genres +
                "} ";
    }

    public void addPlatform(Platform platform) {
        platforms.add(platform);
        platform.getGames().add(this);
    }

    public void addDeveloper(Developer developer) {
        developers.add(developer);
        developer.getGames().add(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getGames().add(this);
    }


}
