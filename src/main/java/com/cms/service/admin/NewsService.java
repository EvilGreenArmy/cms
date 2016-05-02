package com.cms.service.admin;

import com.cms.entities.admin.NewsInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Evan on 2016/5/2.
 */
public interface NewsService {

    Page<NewsInfo> queryList(Map<String, Object> paramMap);

    public void saveNews(NewsInfo news);

    public NewsInfo getNewsById(Integer id);

    public void updateNews(NewsInfo news);

    public void deleteNews(Integer[] ids);
}
