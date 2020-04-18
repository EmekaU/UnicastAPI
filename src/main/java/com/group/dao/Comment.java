package com.group.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@SequenceGenerator(name="comments_seq", initialValue=1, allocationSize=1)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
    @Column()
    private long comment_id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podcast_id")
    @JsonBackReference
    private Podcast podcast;

    public Comment(){}

    public Comment(String content){
        this.content = content;
    }

    public long getComment_id() { return comment_id; }

    public void setComment_id(long comment_id) { this.comment_id = comment_id; }

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
