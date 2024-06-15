package com.example.rabbitmq_api.controllers;

import com.example.rabbitmq_api.models.DTO.post.PostCreatingDTO;
import com.example.rabbitmq_api.models.DTO.post.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/new")
    public String addNewPostForm(Model model) {
        model.addAttribute("post", new PostCreatingDTO());

        return "post_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewPost(@ModelAttribute PostCreatingDTO post) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Creating post.");

        try {
             postService.save(post);
             logger.info("");
             return ResponseEntity
                     .status(HttpStatus.CREATED)
                     .headers(httpHeaders)
                     .body("Post successfully created.");
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .headers(httpHeaders)
                     .body("User or category doesn't exist.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id){

        Optional<PostResponseDTO> postOptional = postService.findById(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting post by id");

        return postOptional
                .map(postDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(postDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/get")
    public ResponseEntity<List<PostResponseDTO>> findAllByUserIdOrUserNameOrCategoryIdOrCategoryName(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String categoryName){

        List<PostResponseDTO> posts = postService.findAllByUserIdOrUserNameOrCategoryIdOrCategoryName(userId, userName, categoryId, categoryName);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all post by user or category id or name");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);

    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){

        List<PostResponseDTO> posts = postService.findAll();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all posts");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Deleting post by id");

        try {
            postService.deleteById(id);
            logger.info("");
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .headers(httpHeaders)
                    .body("Post with id " + id + " has been successfully deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete post with id " + id);
        }
    }
}
