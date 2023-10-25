package com.example.Blog.Controller;

import com.example.Blog.Model.Entity.Post;
import com.example.Blog.Service.PostService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Builder
public class PostController {

    private PostService postService;

    @GetMapping("/")
    public String board(Model model)
    {
        List<Post> postList = postService.getList();
        if(postList.isEmpty())
        {
            postService.create();
            postList = postService.getList();
        }
        model.addAttribute("postList", postList);
        model.addAttribute("selectPost", postList.get(0));

        return "BlogBoard";
    }

    @PostMapping("/create")
    public String create(Model model)
    {
        postService.create();
        return String.format("redirect:/detail/%d",postService.getList().size());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id)
    {
        List<Post> postList = postService.getList();
        model.addAttribute("postList", postList);

        Post selectPost = postService.getPost(id);
        model.addAttribute("selectPost", selectPost);
        return "BlogBoard";
    }

    @PostMapping("/modify")
    public String modify(Long id, String title, String content)
    {
        postService.modify(id, title, content);
        return "redirect:/detail/" + id;
    }
}
