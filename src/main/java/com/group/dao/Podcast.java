package com.group.dao;

import com.group.model.Category;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "podcasts")
@SequenceGenerator(name="podcasts_seq", initialValue=1, allocationSize=1)
public class Podcast {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "podcasts_seq")
    private long podcast_id;

    private Category category; // Not a table, just a data type.

    @Max(250) @Column(name = "title")
    private String title;

    @Max(250) @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdon", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName="user_id")
    private UserDao creator;

    @Column(name = "content", nullable = false)
    private byte[] content;

    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Podcast(){ }

    public Podcast(Category category, String title, String description, byte[] content){
        this.category = category;
        this.title = title;
        this.content = content;
        this.description = description;
        this.creationDate = new Date();
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

    public UserDao getCreator() {
        return creator;
    }

    public void setCreator(UserDao creator) {
        this.creator = creator;
    }

    public byte[] getContent() { return content; }

    public void setContent(byte[] content) { this.content = content; }

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
