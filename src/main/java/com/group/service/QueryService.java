package com.group.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.dao.UserDao;

@Service
public class QueryService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PodcastService podcastService;
	
	public Map<String, List> wildSearch(String query) {
		
		HashMap<String, List> map = new HashMap<>();

		map.put("users", userService.getUsersByUsernameWith(query));
		map.put("podcasts", podcastService.getPodcastsByTitle(query));
		
		return map;
	}
}
