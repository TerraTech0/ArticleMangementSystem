package com.example.articlemangementsystem.Controller;

import com.example.articlemangementsystem.ApiResponse.ApiResponse;
import com.example.articlemangementsystem.Model.NewsArticle;
import com.example.articlemangementsystem.Service.NewsArticleServiec;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;

@RestController
    @RequestMapping("/api/v1/news-article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleServiec newsArticleServiec;


    @GetMapping("/get-news-articles")
    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticleServiec.getNewsArticles();
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleServiec.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("Articles added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticle(@PathVariable int id, @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
            newsArticleServiec.updateArticle(id, newsArticle);
            return ResponseEntity.status(200).body(new ApiResponse("the article updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable int id){
        newsArticleServiec.deleteArticle(id);
        return ResponseEntity.status(200).body(new ApiResponse("Article deleted successfully!"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publish(@PathVariable int id){
        newsArticleServiec.publish(id);
        return ResponseEntity.status(200).body(new ApiResponse("Article published successfully!"));
    }

    @GetMapping("/all-published")
    public ArrayList<NewsArticle> allPublished(){
        return newsArticleServiec.allPublished();
    }


    @GetMapping("/get-category/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        ArrayList<NewsArticle> newsArticles = newsArticleServiec.searchByCategory(category);
        if (!newsArticles.isEmpty()){
            return ResponseEntity.status(200).body(newsArticles);
        }
        return ResponseEntity.status(400).body(new ApiResponse("category not found!"));
    }


}
