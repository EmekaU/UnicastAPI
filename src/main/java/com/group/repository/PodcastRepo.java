package com.group.repository;

import com.group.dao.Podcast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepo extends CrudRepository<Podcast, Long> {

}
