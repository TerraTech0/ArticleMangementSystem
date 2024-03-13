package com.example.articlemangementsystem.Service;

import com.example.articlemangementsystem.Controller.NewsArticleController;
import com.example.articlemangementsystem.Model.NewsArticle;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Service
public class NewsArticleServiec {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateArticle(int id, NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id){
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }
    public void deleteArticle(int id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id){
                newsArticles.remove(i);
            }
        }
    }

    public boolean publish(int id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId() == id){
                if (!newsArticles.get(i).isPublished()){
                    newsArticles.get(i).setPublished(true);
                    return true;
                }
            }
        }
        return false;
    }


    public ArrayList<NewsArticle> allPublished(){
        ArrayList<NewsArticle> allPublish = new ArrayList<>();
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).isPublished()){
                allPublish.add(newsArticles.get(i));
            }
        }
        return allPublish;
    }


    public ArrayList<NewsArticle> searchByCategory(String category){
        ArrayList<NewsArticle> matchingArticles = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles){
            if (newsArticle.getCategory().equalsIgnoreCase(category)){
                matchingArticles.add(newsArticle);
            }
        }
        return matchingArticles;
    }


}

