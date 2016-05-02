package com.cms.service.admin;

import com.cms.entities.admin.MessageInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Reason on 2016/3/28.
 */
public interface MessageService {

    public Page<MessageInfo> queryList(Map<String, Object> paramMap);

    public Page<MessageInfo> queryFrontList(Map<String, Object> paramMap);

    public void saveMessage(MessageInfo message, Integer toUsers);

    public  MessageInfo getMessageById(Integer id);

    public void setRead(Integer id);

    public Integer queryUnreadMessage(Integer id);

}
