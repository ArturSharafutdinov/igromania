package ru.igromania.server.persistence.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
@AttributeOverride(name = "id", column = @Column(name = "AUTHOR_ID"))
public class Author extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Article> articles;

    public void setName(String authorName) {
        this.name = authorName;
    }

    public String getName() {
        return name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return getId() + " " + name + "\n";
    }
}
