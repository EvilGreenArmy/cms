package com.cms.dao.admin;

import com.cms.entities.admin.NewsInfo;
import com.cms.entities.admin.SourceInfo;
import com.cms.entities.admin.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Evan on 2016/5/2.
 */
public interface NewsMapper {
    List<NewsInfo> newsQueryPage(Map map);

    void insertNews(NewsInfo news);

    List<NewsInfo> getNewsById(Integer id);

    void updateNews(NewsInfo news);

    void deleteNews(Integer[] ids);
}
