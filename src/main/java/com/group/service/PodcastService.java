package com.group.service;

import com.group.dao.Podcast;
import com.group.model.Category;
import com.group.model.User;
import com.group.repository.PodcastRepo;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PodcastService {

    @Autowired
    private PodcastRepo podcastRepo;

//    @Autowired
//    public PodcastService(PodcastRepo podcastRepo) {
//        this.podcastRepo = podcastRepo;
//    }

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

    public boolean createPodcast(String token, Map<String, String> body, byte[] content){
        if(! JwtUtils.tokenIsExpired(token) && token != null) {

            Category category = getCategory(body.get("category"));
            String title = body.get("title");
            String description = body.get("description");

            User user = JwtUtils.decodeUser(token);
            if(user != null){
                Podcast podcast = new Podcast(category, title, description, content);
                // TODO: podcast.setCreator(); -  Is this necessary?
                podcastRepo.save(podcast);
                return true;
            }
        }

        return false;
    }
}
