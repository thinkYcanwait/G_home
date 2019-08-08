package it.gong.yunge.service.serviceImpl;

import it.gong.yunge.dao.UserDao;
import it.gong.yunge.dao.daoImpl.UserDaoImpl;
import it.gong.yunge.domain.User;
import it.gong.yunge.service.UserService;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao u = new UserDaoImpl();
    @Override
    public boolean addUser(User user) {
        return u.addUser(user);
    }

    @Override
    public List<User> selectByUser(User user) {
        return u.selectByUser(user);
    }

    @Override
    public boolean addUserNote(String words, String realpath,int writerid,String noteName) {
        boolean falg = u.addUserNote(writerid,noteName);
        if(falg){
            try {
                File userNote = new File(realpath);
                FileWriter fw = new FileWriter(userNote);
                fw.write(words);
                fw.flush();
                fw.close();
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addUserArticle(String words, String realPath, int writerId, String articleName) {
        boolean falg = u.addUserArticle(writerId,articleName);
        if(falg){
            try {
                File userNote = new File(realPath);
                FileWriter fw = new FileWriter(userNote);
                fw.write(words);
                fw.flush();
                fw.close();
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }
}
