package it.gong.yunge.dao.daoImpl;

import it.gong.yunge.dao.UserDao;
import it.gong.yunge.domain.User;
import it.gong.yunge.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(DruidUtil.getDs());

    @Override
    public boolean addUser(User user) {
        String sql = "insert into users(username,password,tel,email) values(?,?,?,?)";
        int i = jt.update(sql, user.getUsername(), user.getPassword(), user.getTel(), user.getEmail());
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> selectByUser(User user) {
        String s = "select * from users where 1=1 ";
        String username = user.getUsername();
        String password = user.getPassword();
        StringBuilder sb = new StringBuilder(s);
        List<String> value = new ArrayList<>();
        if (username != null) {
            sb.append(" and username = ? ");
            value.add(username);
        }
        if(password!=null){
            sb.append(" and password = ? ");
            value.add(password);
        }
        String sql = sb.toString();
        List<User> list = jt.query(sql, new BeanPropertyRowMapper<User>(User.class), value.toArray());
        return list;
    }

    @Override
    public boolean addUserNote(int writerid, String noteName) {
        String sql = "insert into notes(notename,writer_id) values(?,?)";
        int update = jt.update(sql, noteName, writerid);
        if(update==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addUserArticle(int writerId, String articleName) {
        String sql = "insert into articles(articlename,writer_id) values(?,?)";
        int update = jt.update(sql, articleName, writerId);
        if(update==1){
            return true;
        }else {
            return false;
        }
    }
}
