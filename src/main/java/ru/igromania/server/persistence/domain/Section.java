package ru.igromania.server.persistence.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SECTION")
@AttributeOverride(name = "id", column = @Column(name = "SECTION_ID"))
public class Section extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<Article> articles;

    public void setName(String sectionName) {
        this.name = sectionName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getId() + " " + name + "\n";
    }
}
