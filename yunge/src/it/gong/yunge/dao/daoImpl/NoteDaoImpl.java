package it.gong.yunge.dao.daoImpl;

import it.gong.yunge.dao.NoteDao;
import it.gong.yunge.domain.Note;
import it.gong.yunge.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class NoteDaoImpl implements NoteDao {
    private JdbcTemplate jt = new JdbcTemplate(DruidUtil.getDs());
    @Override
    public List<Note> selectNewNote() {
        String sql ="SELECT n.`id`,n.`notename`,n.`writer_id`,n.`readcount`,n.`likecount`,n.`writetime`,u.`username` FROM users u,notes n WHERE u.`id`= n.`writer_id` ORDER BY writetime DESC LIMIT 0 , 3";
        List<Note> list = jt.query(sql, new BeanPropertyRowMapper<Note>(Note.class));
        return list;
    }
}
