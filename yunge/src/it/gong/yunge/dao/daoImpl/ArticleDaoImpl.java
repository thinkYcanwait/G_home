package it.gong.yunge.dao.daoImpl;

import it.gong.yunge.dao.ArticleDao;
import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.User;
import it.gong.yunge.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    private JdbcTemplate jt = new JdbcTemplate(DruidUtil.getDs());
    @Override
    public List<Article> selectNewArticle() {
        String sql = "SELECT n.`id`,n.`articlename`,n.`writer_id`,n.`readcount`,n.`likecount`,n.`writetime`,u.`username` FROM users u,articles n WHERE u.`id`= n.`writer_id` ORDER BY writetime DESC LIMIT 0 , 3";
        return jt.query(sql,new BeanPropertyRowMapper<Article>(Article.class));
    }

    @Override
    public List<Article> selectArticleByUser(User user) {
        int id = user.getId();
        String sql = "select * from articles where writer_id = ?";
        return jt.query(sql,new BeanPropertyRowMapper<Article>(Article.class),id);
    }
}
