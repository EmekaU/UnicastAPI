package com.group.service;

import com.group.dao.Comment;
import com.group.dao.Podcast;
import com.group.repository.PodcastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group.repository.CommentRepo;
@Service
public class CommentService {

    private PodcastRepo podcastRepo;
    private CommentRepo commentRepo;

    @Autowired
    public CommentService(PodcastRepo podcastRepo, CommentRepo commentRepo){

        this.podcastRepo = podcastRepo;
        this.commentRepo = commentRepo;
    }
//
//    public void addComment(String message, long podcast_id){
//
//        Comment comment = new Comment(message);
//        Podcast podcast = this.podcastRepo.getPodcastByPodcast_id(podcast_id);
//        comment.setPodcast(podcast);
//
//        this.commentRepo.save(comment);
//    }
//
//    public boolean deleteComment(Long commentid){
//        Comment comment = this.commentRepo.getCommentByComment_id(commentid);
//        if(comment != null){
//            this.commentRepo.delete(comment);
//            return true;
//        }
//        return false;
//    }

}
