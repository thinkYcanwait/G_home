package it.gong.yunge.dao;

import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.User;

import java.util.List;

public interface ArticleDao {
    List<Article> selectNewArticle();

    List<Article> selectArticleByUser(User user);
}
