package it.gong.yunge.dao;

import it.gong.yunge.domain.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);

    List<User> selectByUser(User user);

    boolean addUserNote(int writerid, String noteName);

    boolean addUserArticle(int writerId, String articleName);
}
