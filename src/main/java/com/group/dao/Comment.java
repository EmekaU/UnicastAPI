package com.group.dao;

import javax.persistence.*;

@Table(name = "comments")
@SequenceGenerator(name="comments_id_seq", initialValue=1, allocationSize=1)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    @Column(nullable = false)
    private Podcast podcast;

    public Comment(){

    }

    public Comment(long id, String content, Podcast podcast){
        this.id = id;
        this.content = content;
        this.podcast = podcast;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", podcast=" + podcast +
                '}';
    }
}
