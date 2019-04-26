package com.springtest.service.impl;

import com.springtest.model.News;
import com.springtest.model.User;
import com.springtest.repo.NewsRepository;
import com.springtest.service.NewsService;
import com.springtest.service.ReflectionService;
import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Qualifier("newsRepository")
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReflectionService reflectionService;

    @Override
    public List<News> getAll() {

        return newsRepository.findAll();
    }

    @Override
    public void saveRecordFromRequest(HttpServletRequest request) {
        News news = (News) reflectionService.getObjectFromRequest(News.class, request);

        news.setViewsAmount(0L);

        newsRepository.save(news);
    }

    @Override
    public void saveEditFromRequest(HttpServletRequest request) {
        News news = new News();
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Long userId = Long.valueOf(request.getParameter("user"));
        Long viewsAmount = Long.valueOf(request.getParameter("viewsAmount"));

        User user = userService.getOne(userId);

        news.setId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setUser(user);
        news.setViewsAmount(viewsAmount);

        newsRepository.save(news);
    }

    @Override
    public News getOne(Long id, Boolean incrementViews) {
        if (incrementViews) {
            newsRepository.increaseViewsAmount(id);
        }
        return newsRepository.findOne(id);

    }

    @Override
    public void delete(Long id) {
        newsRepository.delete(id);
    }

    @Override
    public HashMap<String, List<Object>> getComboboxOptions() {    //тут будут все доступные варианты для разных выпадающих списков при редактировании новостей
        HashMap<String, List<Object>> options = new HashMap<String, List<Object>>();
        List<Object> users = new ArrayList<Object>(userService.getAll());

        options.put("users", users);
        return options;
    }
}