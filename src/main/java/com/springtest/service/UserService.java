package com.springtest.service;

import com.springtest.model.News;
import com.springtest.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserService {
    List<User> getAll();

    void saveRecordFromRequest(HttpServletRequest request);

    void saveEditFromRequest(HttpServletRequest request);

    User getOne(Long id);

    void delete(Long id);

    List<News> getNewsByUser(Long id);

    boolean updatePassword(String newPassword, String validPassword, Long id);


}
