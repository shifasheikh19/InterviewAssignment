package com.example.blog.controller;
import com.example.blog.model.BlogRequest;
import com.example.blog.model.Blog;

import com.example.blog.service.BlogService;
import com.example.blog.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private OpenAIService openAIService;

    // Get all blogs
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    // Get a blog by ID
    @GetMapping("/{id}")
public ResponseEntity<?> getBlogById(@PathVariable Long id) {
    Optional<Blog> blog = blogService.getBlogById(id);
    
    if (blog.isPresent()) {
        return ResponseEntity.ok(blog.get());
    } else {
        return ResponseEntity.status(404).body("Blog not found");
    }
}


    // Create a new blog
    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogRequest blogRequest) {
        if (blogRequest.getTitle() == null || blogRequest.getContent() == null) {
            return ResponseEntity.badRequest().body("Title and content are required.");
        }
        
        Blog blog = new Blog();
        blog.setTitle(blogRequest.getTitle());
        blog.setContent(blogRequest.getContent());

        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(createdBlog);
    }

    // Update a blog by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody BlogRequest blogRequest) {
        if (blogRequest.getTitle() == null || blogRequest.getContent() == null) {
            return ResponseEntity.badRequest().body("Title and content cannot be empty.");
        }

        Blog updatedBlog = new Blog();
        updatedBlog.setTitle(blogRequest.getTitle());
        updatedBlog.setContent(blogRequest.getContent());

        Blog result = blogService.updateBlog(id, updatedBlog);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.status(404).body("Blog not found");
    }

    // Delete a blog by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        if (blogService.getBlogById(id).isEmpty()) {
            return ResponseEntity.status(404).body("Blog not found");
        }
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully!");
    }

    // Summarize a blog using OpenAI API
    @PostMapping("/summarize")
    public ResponseEntity<?> summarizeBlog(@RequestBody BlogRequest blogRequest) {
        if (blogRequest.getContent() == null || blogRequest.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Blog content cannot be empty.");
        }
        String summarizedContent = openAIService.getSummarizedContent(blogRequest.getContent());
        return ResponseEntity.ok(summarizedContent);
    }

    // Test endpoint
    @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }
}
