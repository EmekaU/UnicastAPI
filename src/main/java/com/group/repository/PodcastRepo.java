package com.group.repository;

import com.group.dao.PodcastDAO;
import org.springframework.data.repository.CrudRepository;

public interface PodcastRepo extends CrudRepository<PodcastDAO, Long> {

}
