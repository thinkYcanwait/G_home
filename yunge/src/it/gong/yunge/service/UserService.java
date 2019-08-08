package it.gong.yunge.service;

import it.gong.yunge.domain.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    List<User> selectByUser(User user);

    boolean addUserNote(String s, String words, int id, String username);

    boolean addUserArticle(String words, String realPath, int writerId, String articleName);
}
