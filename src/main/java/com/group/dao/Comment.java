package com.group.dao;

import javax.persistence.*;

@Table(name = "comments")
@SequenceGenerator(name="comments_comment_id_seq", initialValue=1, allocationSize=1)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long comment_id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    @Column(nullable = false)
    private Podcast podcast;

    public Comment(){}

    public Comment(long comment_id, String content){
        this.content = content;
    }

    public long getId() { return comment_id; }

    public void setId(long comment_id) { this.comment_id = comment_id; }

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
                "id=" + comment_id +
                ", content='" + content + '\'' +
                ", podcast=" + podcast +
                '}';
    }
}
