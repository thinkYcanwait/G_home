package it.gong.yunge.service;

import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.User;

import java.util.List;

public interface ArticleService {
    List<Article> selectNewArticle();

    List<Article> selectArticleByUser(User user);
}
