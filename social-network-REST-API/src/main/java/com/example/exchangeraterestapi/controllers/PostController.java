package com.example.exchangeraterestapi.controllers;

import com.example.exchangeraterestapi.DTO.PostDTO;
import com.example.exchangeraterestapi.entitys.PostEntity;
import com.example.exchangeraterestapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
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

    @GetMapping("/new")
    public String addNewPost(Model model) {
        model.addAttribute("post", new PostEntity());

        return "post_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewPost(@ModelAttribute PostEntity postEntity) {
        postService.save(postEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Post successfully created");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body("Post successfully created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        Optional<PostDTO> postOptional = postService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting post by id");

        return postOptional
                .map(postDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(postDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/get/{userID}")
    public ResponseEntity<List<PostDTO>> getAllPostByUserId(@PathVariable Long userID){
        List<PostDTO> posts = postService.findAllByUserId(userID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all post by user id");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);
    }

    @GetMapping("/get/{userName}")
    public ResponseEntity<List<PostDTO>> getAllPostByUserName(@PathVariable String userName){
        List<PostDTO> posts = postService.findAllByUserName(userName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all post by user name");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);
    }

    @GetMapping("/get/{categoryID}")
    public ResponseEntity<List<PostDTO>> getAllPostByCategoryId(@PathVariable Long categoryID){
        List<PostDTO> posts = postService.findAllByCategoryId(categoryID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all post by category id");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);
    }

    @GetMapping("/get/{categoryName}")
    public ResponseEntity<List<PostDTO>> getAllPostByCategoryName(@PathVariable String categoryName){
        List<PostDTO> posts = postService.findAllByCategoryName(categoryName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all post by category name");

        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(posts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> posts = postService.findAll();
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .headers(httpHeaders)
                    .body("Post with ID " + id + " has been successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete post with ID " + id);
        }
    }
}
