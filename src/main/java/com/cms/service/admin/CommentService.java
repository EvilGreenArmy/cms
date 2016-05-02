package com.cms.service.admin;

import com.cms.entities.admin.CommentInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Evan on 2016/4/3.
 */
public interface CommentService {
    public Page<CommentInfo> queryList(Map<String, Object> paramMap);

    public void saveComment(CommentInfo commentInfo);

    public void deleteComment(Integer[] ids);
}
