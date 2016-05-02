package com.cms.service.admin;

import com.cms.entities.admin.CompetitionInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Evan on 2016/4/3.
 */
public interface CompetitionService {

    public Page<CompetitionInfo> queryList(Map<String, Object> paramMap);

    public void saveCompetition(CompetitionInfo competition);

    public void deleteCompetition(Integer[] ids);
}
