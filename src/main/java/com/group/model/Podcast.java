package com.group.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@SequenceGenerator(name="#whatever name we assign to this table's id", initialValue=1, allocationSize=1)
@Table(name = "podcasts")
public class Podcast {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Category category; // Not a table, just a data type.

    @Max(250)
    private String title;

    @Max(250)
    private String description;

    private int length;
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", creationDate=" + creationDate +
                ", creator=" + creator +
                '}';
    }
}
