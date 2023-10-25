package com.example.Blog.Service;

import com.example.Blog.Model.Entity.Post;
import com.example.Blog.Model.Repository.PostRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Builder
public class PostService {

    private PostRepository postRepository;

    public void create()
    {
        Post post = new Post();
        post.setTitle("new title");
        post.setContent("");
        post.setLocalDateTime(LocalDateTime.now());

        this.postRepository.save(post);
    }

    public List<Post> getList()
    {
        return this.postRepository.findAll();
    }

    public Post getPost(Long id)
    {
        return this.postRepository.findById(id).orElse(null);
    }

    public void modify(Long id, String title, String content)
    {
        Post post = getPost(id);

        assert post != null;

        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);
    }

}
