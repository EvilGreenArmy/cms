package com.cms.service.admin;

import com.cms.entities.admin.FavoritesInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Evan on 2016/4/3.
 */
public interface FavoritesService {

    public Page<FavoritesInfo> queryList(Map<String, Object> paramMap);

    public void saveFavorites(FavoritesInfo favoritesInfo);

    public void deleteFavorites(Integer[] ids);
}
