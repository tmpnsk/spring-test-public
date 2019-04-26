package com.springtest.service;


import com.springtest.model.News;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface NewsService {

    List<News> getAll();

    void saveRecordFromRequest(HttpServletRequest request);

    void saveEditFromRequest(HttpServletRequest request);

    News getOne(Long id, Boolean incrementViews);

    void delete(Long id);

    HashMap<String, List<Object>> getComboboxOptions();
}