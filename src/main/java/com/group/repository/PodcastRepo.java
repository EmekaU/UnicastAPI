package com.group.repository;
//
import com.group.dao.Podcast;
//import com.group.model.Category;
import com.group.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PodcastRepo extends CrudRepository<Podcast, Long> {

//    public boolean deletePodcastByPodcast_id(long id);

    public List<Podcast> getPodcastByCreator_Username(String username);

    public List<Podcast> getPodcastByCategory(Category category);

    public List<Podcast> getPodcastsByTitleIsContainingOrderByTitleAsc(String match);

    public List<Podcast> getPodcastsByCreationDateOrderByCreationDate(Date date);

    public List<Podcast> getPodcastsByCreatorUsername(String username);

}
