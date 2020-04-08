package com.group.repository;

import com.group.dao.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {
//
//    public boolean getCommentsByPodcast_idOrderByComment_id(long id);
//
//    public Comment getCommentByComment_id(long id);
}
