package com.group.repository;

import com.group.dao.Podcast;
//import com.group.model.Category;
import com.group.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

@Repository
public interface PodcastRepo extends CrudRepository<Podcast, Long> {

    public Podcast getPodcastById(long id);

    public boolean existsByTitleAndCreatorId(String title, long creator_id);

    public List<Podcast> getPodcastsByCreator_Username(String username);

    public List<Podcast> getPodcastByCategory(String category);

    public List<Podcast> getPodcastsByTitleIsContainingOrderByTitleAsc(String match);

    public List<Podcast> getPodcastsByCreationDateBeforeOrCreationDateEquals(Date creationDate, Date creationDate2);

    public boolean existsByCreator_Username(String username);

    public List<Podcast> getAllByCreatorNotNull();
}
