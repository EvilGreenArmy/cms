package com.cms.service.admin.impl;

import com.cms.dao.admin.FavoritesMapper;
import com.cms.entities.admin.FavoritesInfo;
import com.cms.pagination.Page;
import com.cms.service.admin.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Evan on 2016/4/3.
 */
@Service("favoritesService")
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    private FavoritesMapper dao;

    @Override
    public Page<FavoritesInfo> queryList(Map<String, Object> paramMap) {
        Page<FavoritesInfo> page = (Page<FavoritesInfo>) paramMap.get("page");
        List<FavoritesInfo> result = dao.favoritesQueryPage(paramMap);
        page.setResultList(result);
        return page;
    }

    @Transactional
    public void saveFavorites(FavoritesInfo favoritesInfo) {
        dao.insertFavorites(favoritesInfo);
    }

    @Transactional
    public void deleteFavorites(Integer[] ids) {
        dao.deleteFavorites(ids);
    }
}
