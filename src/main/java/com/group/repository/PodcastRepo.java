package com.group.repository;

import com.group.model.Podcast;
import org.springframework.data.repository.CrudRepository;

public interface PodcastRepo extends CrudRepository<Podcast, Long> {

}
