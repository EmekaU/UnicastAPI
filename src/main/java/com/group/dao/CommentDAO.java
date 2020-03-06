package com.group.dao;

import javax.persistence.*;

@Table(name = "comments")
@SequenceGenerator(name="comments_id_seq", initialValue=1, allocationSize=1)
public class CommentDAO {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(nullable = false)
    private PodcastDAO podcastDAO;

    public CommentDAO(){

    }

    public CommentDAO(long id, String content, PodcastDAO podcastDAO){
        this.id = id;
        this.content = content;
        this.podcastDAO = podcastDAO;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public PodcastDAO getPodcastDAO() {
        return podcastDAO;
    }

    public void setPodcastDAO(PodcastDAO podcastDAO) {
        this.podcastDAO = podcastDAO;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", podcast=" + podcastDAO +
                '}';
    }
}
