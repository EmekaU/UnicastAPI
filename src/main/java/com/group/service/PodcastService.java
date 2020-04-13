package com.group.service;

import com.group.dao.Podcast;
import com.group.model.Category;
import com.group.model.User;
import com.group.repository.PodcastRepo;
import com.group.repository.UserRepo;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PodcastService {

    private PodcastRepo podcastRepo;
    private UserRepo userRepo;

    @Autowired
    public PodcastService(PodcastRepo podcastRepo, UserRepo userRepo) {

        this.podcastRepo = podcastRepo;
        this.userRepo = userRepo;
    }

    private Category getCategory(String categoryField){

        switch(categoryField.toLowerCase()){
            case "comedy": return Category.Comedy;
            case "sports": return Category.Sports;
            case "politics": return Category.Politics;
            case "education": return Category.Education;
            case "religion": return Category.Religion;
            case "lifestyle": return Category.Lifestyle;
            default: return Category.Misc;
        }
    }

    public boolean createPodcast(String token, Map<String, String> body){
        if(! JwtUtils.tokenIsExpired(token) && token != null) {

            Category category = getCategory(body.get("category"));
            String title = body.get("title");
            byte[] content = body.get("content").getBytes();
            String description = body.get("description");

            User user = JwtUtils.decodeUser(token);
            Podcast podcast = new Podcast(category, title, description, content);
            podcast.setCreator(this.userRepo.getUserDaoByUsername(user.getUsername()));
            podcastRepo.save(podcast);
            return true;
        }

        return false;
    }

    public List<Podcast> getPodcastsBelongingTo(String username){

        return this.podcastRepo.getPodcastByCreator_Username(username);
    }

//    public List<Podcast> getPodcastsByCategory(String category){
//
//        return this.getPodcastsByCategory(category);
//    }

    public List<Podcast> getPodcastsByTitle(String query){

        return podcastRepo.getPodcastsByTitleIsContainingOrderByTitleAsc(query);
    }

}
