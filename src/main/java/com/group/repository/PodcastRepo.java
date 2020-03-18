package com.group.repository;

import com.group.dao.Podcast;
import com.group.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PodcastRepo extends CrudRepository<Podcast, Long> {

    public boolean deletePodcastByPodcast_id(long id);

    public Podcast getPodcastById(long id);

    public List<Podcast> getPodcastByCategory(Category category);

    public List<Podcast> getPodcastsByTitleIsContaining(String match);

    public List<Podcast> getPodcastsByCreationDateOrderByCreationDate(Date date);

    public List<Podcast> getPodcastsByCreatorUsername(String username);

}
