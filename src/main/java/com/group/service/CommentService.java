package com.group.service;

import com.group.dao.Comment;
import com.group.dao.Podcast;
import com.group.repository.PodcastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group.repository.CommentRepo;
@Service
public class CommentService {

    private final PodcastRepo podcastRepo;
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(PodcastRepo podcastRepo, CommentRepo commentRepo){

        this.podcastRepo = podcastRepo;
        this.commentRepo = commentRepo;
    }

    public boolean addComment(String message, long podcast_id){

        Comment comment = new Comment(message);
        Podcast podcast = this.podcastRepo.getPodcastById(podcast_id);
        comment.setPodcast(podcast);

        this.commentRepo.save(comment);
        return true;
    }

}
