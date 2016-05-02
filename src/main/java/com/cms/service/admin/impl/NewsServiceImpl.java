package com.cms.service.admin.impl;

import com.cms.dao.admin.NewsMapper;
import com.cms.entities.admin.NewsInfo;
import com.cms.pagination.Page;
import com.cms.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Evan on 2016/5/2.
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper dao;

    @Override
    public Page<NewsInfo> queryList(Map<String, Object> paramMap) {
        Page<NewsInfo> page = (Page<NewsInfo>) paramMap.get("page");
        List<NewsInfo> result = dao.newsQueryPage(paramMap);
        page.setResultList(result);
        return page;
    }

    @Transactional
    public void saveNews(NewsInfo news) {
        dao.insertNews(news);
    }

    @Override
    public NewsInfo getNewsById(Integer id) {
        List<NewsInfo> newsList = dao.getNewsById(id);
        if (newsList != null && newsList.size() > 0) {
            return newsList.get(0);
        }
        return new NewsInfo();
    }

    @Transactional
    public void updateNews(NewsInfo news) {
        dao.updateNews(news);
    }

    @Transactional
    public void deleteNews(Integer[] ids) {
        dao.deleteNews(ids);
    }
}
