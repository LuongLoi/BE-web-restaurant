package com.intern.restaurant.service;

import java.util.List;

import com.intern.restaurant.dto.PostDTO;
import com.intern.restaurant.model.Post;

public interface PostService {
	public List<PostDTO> listPost();
	public List<PostDTO> listPostByCurrentUser();
	public Post createPost(Post post);
	
	public boolean updateTitle(int id, String title);
	public boolean updateThumbnail(int id, String thumbnail);
	public boolean updatePublished(int id, int published);
	
	public void deleteById(int id);
	public void deleteAllByCategoryId(int cf_id);
}
