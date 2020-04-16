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
            case "comedy": return Category.comedy;
            case "sports": return Category.sports;
            case "politics": return Category.politics;
            case "education": return Category.education;
            case "religion": return Category.religion;
            case "lifestyle": return Category.lifestyle;
            default: return Category.misc;
        }
    }

    public boolean createPodcast(String token, Map<String, String> body){
        if(! JwtUtils.tokenIsExpired(token) && token != null) {

            User user = JwtUtils.decodeUser(token);
            String title = body.get("title");
            String url = body.get("url");
            String description = body.get("description");

            Podcast podcast = new Podcast(getCategory(body.get("category")).toString(), title, description, url);
            podcast.setCreator(this.userRepo.getUserDaoByUsername(user.getUsername()));

            System.out.println(podcast.toString());

            podcastRepo.save(podcast);
            return true;
        }

        return false;
    }

    public List<Podcast> getPodcastsBelongingTo(String username){

        return this.podcastRepo.getPodcastByCreator_Username(username);
    }

    public Podcast getPodcastById(long id){
        return this.podcastRepo.getPodcastById(id);
    }

    public List<Podcast> getPodcastsByCategory(String category){

        return this.podcastRepo.getPodcastByCategory(category);
    }

    public List<Podcast> getPodcastsByTitle(String query){

        return podcastRepo.getPodcastsByTitleIsContainingOrderByTitleAsc(query);
    }

}
