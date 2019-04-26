package com.springtest.service;

import com.springtest.model.Comment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface CommentService {

    void saveEditFromRequest(HttpServletRequest request, Long id);

    List<Comment> findByNewsId(Long id);

    void like(Long idComment);

    void dislike(Long idComment);

}
