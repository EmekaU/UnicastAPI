package com.group.service;

import com.group.dao.Comment;
import com.group.dao.Podcast;
import com.group.model.Category;
import com.group.model.User;
import com.group.repository.PodcastRepo;
import com.group.repository.UserRepo;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PodcastService {

    private final PodcastRepo podcastRepo;
    private final UserRepo userRepo;

    @Autowired
    public PodcastService(PodcastRepo podcastRepo, UserRepo userRepo) {

        this.podcastRepo = podcastRepo;
        this.userRepo = userRepo;
    }

    private Category getCategory(String categoryField){

        switch(categoryField.toLowerCase()){
            case "comedy": return Category.comedy;
            case "sports": return Category.sports;
            case "politics": return Category.politics;
            case "education": return Category.education;
            case "religion": return Category.religion;
            case "lifestyle": return Category.lifestyle;
            default: return Category.misc;
        }
    }

    public List<Podcast> createPodcast(String token, Map<String, String> body){
        User user = JwtUtils.decodeUser(token); // Check that token is not compromised
        String title = body.get("title");
        String url = body.get("url");
        String description = body.get("description");

        Podcast podcast = new Podcast(getCategory(body.get("category")).toString(), title, description, url);
        podcast.setCreator(this.userRepo.getUserDaoByUsername(user.getUsername()));

//        If user has used the same title, return error message. To avoid SQL Check Constraint Error
        if(this.podcastRepo.existsByTitleAndCreatorId(podcast.getTitle(), podcast.getCreator().getId())){
            return null;
        }

        podcastRepo.save(podcast);
        return this.getPodcastsBelongingTo(user.getUsername());
    }

    public List<Podcast> getPodcastsBelongingTo(String username){
        if(this.podcastRepo.existsByCreator_Username(username)){
            return this.podcastRepo.getPodcastsByCreator_Username(username);
        }

        return null;
    }

    public Podcast getPodcastById(long id){
        if(podcastRepo.existsById(id)){
            return this.podcastRepo.getPodcastById(id);
        }
        return null;
    }

    public List<Podcast> getPodcastsByCategory(String category){

        return this.podcastRepo.getPodcastByCategory(this.getCategory(category).toString());
    }

    public List<Podcast> getPodcastsByTitle(String query){

        return podcastRepo.getPodcastsByTitleIsContainingOrderByTitleAsc(query);
    }

    public List<Podcast> getRecentPodcasts(){
        return this.podcastRepo.getPodcastsByCreationDateBeforeOrCreationDateEquals(new Date(), new Date());
    }

    public List<Podcast> getAll(){
        return this.podcastRepo.getAllByCreatorNotNull();
    }

    public List<Comment> getCommentsBelongingToPodcastWithId(long id){
        Podcast podcast = this.getPodcastById(id);
        if(podcast != null){

            return podcast.getComments();
        }
        return null;
    }
}
