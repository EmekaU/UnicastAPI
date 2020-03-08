package com.group.dao;

import com.group.model.Category;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Table(name = "podcasts")
@SequenceGenerator(name="podcasts_id_seq", initialValue=1, allocationSize=1)
public class Podcast {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long podcast_id;

    private Category category; // Not a table, just a data type.

    @Max(250)
    private String title;

    @Max(250)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdon")
    private Date creationDate = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User creator;

    @Column(nullable = false)
    private byte[] content;

    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Podcast(){ }

    public Podcast(Category category, String title, String description, long user_id, byte[] file){
        this.category = category;
        this.title = title;
        // set user?
        this.description = description;
    }

    public Long getId() {
        return podcast_id;
    }

    public void setId(Long id) {
        this.podcast_id = id;
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

    public byte[] getContent() { return content; }

    public void setContent(byte[] content) { this.content = content; }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment){
        this.comments.add(comment);
        comment.setPodcast(this);
    }
    
    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + podcast_id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", creator=" + creator +
                ", file=" + Arrays.toString(content) +
                '}';
    }
}
