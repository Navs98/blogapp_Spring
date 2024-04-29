package com.spring.blogapp_Springboot.articles;

import com.spring.blogapp_Springboot.articles.dtos.CreateArticleRequest;
import com.spring.blogapp_Springboot.articles.dtos.UpdateArticleRequest;
import com.spring.blogapp_Springboot.users.UserService;
import com.spring.blogapp_Springboot.users.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticlesRepository articlesRepository;
    private UsersRepository usersRepository;
    public ArticleService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articlesRepository.findBySlug(slug);
        throw new ArticleNotFoundException(slug);
    }

    public ArticleEntity createArticle(CreateArticleRequest req, Long autherId) {
        var author = usersRepository.findById(autherId).orElseThrow(()->new UserService.UserNotFoundException(autherId));
     return articlesRepository.save(ArticleEntity.builder()
             .title(req.getTitle())
             .slug(req.getTitle().toLowerCase().replaceAll("\\s+", "-"))
             .body(req.getBody())
             .subtitle(req.getSubtitle())
             .author(author)
             .build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest req) {
        var article = articlesRepository.findById(articleId).orElseThrow(()->new ArticleService.ArticleNotFoundException(articleId));;

        if(req.getTitle() != null){
            article.setTitle(req.getTitle());
            article.setSlug(req.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(req.getBody() != null){
            article.setBody(req.getBody());
        }
        if(req.getSubtitle() != null){
            article.setSubtitle(req.getSubtitle());
        }
        return articlesRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("Article: " + slug + " not found");
        }
        public ArticleNotFoundException(Long id) {
            super("Article with id: " + id + " not found");
        }
    }
}
