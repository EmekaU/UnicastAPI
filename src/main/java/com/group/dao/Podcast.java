package com.group.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "podcasts")
@SequenceGenerator(name="podcasts_seq", initialValue=1, allocationSize=1)
public class Podcast {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "podcasts_seq")
    @Column(name = "podcast_id")
    private long id;

    @Column(name = "category")
    private String category;

    @Max(250) @Column(name = "title")
    private String title;

    @Max(250) @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdon", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName="user_id")
    @JsonBackReference
    private UserDao creator;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;

    public Podcast(){ }

    public Podcast(String category, String title, String description, String url){
        this.category = category;
        this.title = title;
        this.url = url;
        this.description = description;
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserDao getCreator() {
        return creator;
    }

    public void setCreator(UserDao creator) {
        this.creator = creator;
    }

    public String getUrl() { return url; }

    public void setContent(String url) { this.url = url; }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setPodcast(this);
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", creator=" + creator +
                ", filepath=" + url +
                '}';
    }
}
