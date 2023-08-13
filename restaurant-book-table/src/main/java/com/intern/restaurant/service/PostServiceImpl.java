package com.intern.restaurant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.restaurant.dto.PostDTO;
import com.intern.restaurant.exception.PostException;
import com.intern.restaurant.mapper.PostMapper;
import com.intern.restaurant.model.Post;
import com.intern.restaurant.model.User;
import com.intern.restaurant.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<PostDTO> listPost() {
		// TODO Auto-generated method stub
		List<PostDTO> listPostDto = new ArrayList<>();
		for (Post post: postRepository.findAll()) 
			listPostDto.add(PostMapper.toPostDTO(post));
		if (listPostDto.size() == 0)
			throw new PostException("Post is not found");
		return listPostDto;
	}

	@Override
	public List<PostDTO> listPostByCurrentUser() {
		// TODO Auto-generated method stub
		int id = userService.getCurrentUserLogin();
		List<PostDTO> listPost = new ArrayList<>();
		for (Post post: postRepository.findAll()) { 
			if (post.getUser().getUs_id() == id)
				listPost.add(PostMapper.toPostDTO(post));
		}
		if (listPost.size() == 0)
			throw new PostException("Post is not found");
		return listPost;
	}

	@Override
	public Post createPost(Post post) {
		// TODO Auto-generated method stub
		post.setUser(new User(userService.getCurrentUserLogin()));
		post.setCreated_date(LocalDateTime.now());
		return postRepository.save(post);
	}

	@Override
	public boolean updateTitle(int id, String title) {
		// TODO Auto-generated method stub
		Optional<Post> o_post = postRepository.findById(id);
		int us_id = userService.getCurrentUserLogin();
		if (o_post.isPresent() && o_post.get().getUser().getUs_id() == us_id) {
			Post post = o_post.get();
			post.setTitle(title);
			postRepository.save(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateThumbnail(int id, String thumbnail) {
		// TODO Auto-generated method stub
		Optional<Post> o_post = postRepository.findById(id);
		int us_id = userService.getCurrentUserLogin();
		if (o_post.isPresent() && o_post.get().getUser().getUs_id() == us_id) {
			Post post = o_post.get();
			post.setThumbnail(thumbnail);
			postRepository.save(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePublished(int id, int published) {
		// TODO Auto-generated method stub
		Optional<Post> o_post = postRepository.findById(id);
		if (o_post.isPresent()) {
			Post post = o_post.get();
			post.setPublished(published);
			postRepository.save(post);
			return true;
		}
		return false;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Optional<Post> o_post = postRepository.findById(id);
		int us_id = userService.getCurrentUserLogin();
		if (o_post.isPresent() && o_post.get().getUser().getUs_id() == us_id) {
			postRepository.deleteById(id);
		}
	}

	@Override
	public void deleteAllByCategoryId(int cf_id) {
		// TODO Auto-generated method stub
		for (Post post : postRepository.findAll()) {
			if (post.getCategory().getCf_id() == cf_id) {
				postRepository.deleteById(post.getPs_id());
			}
		}
		
	}

}
