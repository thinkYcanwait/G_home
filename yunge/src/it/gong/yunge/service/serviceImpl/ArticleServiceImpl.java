package it.gong.yunge.service.serviceImpl;

import it.gong.yunge.dao.ArticleDao;
import it.gong.yunge.dao.daoImpl.ArticleDaoImpl;
import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.User;
import it.gong.yunge.service.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao a = new ArticleDaoImpl();
    @Override
    public List<Article> selectNewArticle() {
        return a.selectNewArticle();
    }

    @Override
    public List<Article> selectArticleByUser(User user) {
        return a.selectArticleByUser(user);
    }
}
