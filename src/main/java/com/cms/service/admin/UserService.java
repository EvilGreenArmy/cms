package com.cms.service.admin;

import com.cms.entities.admin.UserInfo;
import com.cms.pagination.Page;

import java.util.Map;

/**
 * Created by Evan on 2016/3/15.
 */
public interface UserService {

    Page<UserInfo> queryList(Map<String, Object> paramMap);

    public void saveUser(UserInfo user);

    public UserInfo getUserByLogin(String userName, String password);

    public boolean checkAcctName(UserInfo userInfo);

    public UserInfo getUserById(Integer id);

    public void updateUser(UserInfo user);

    public void deleteUser(Integer[] ids);

    public void editAccountDuty(Map<String, Object> map);

    public boolean isAdmin(Integer acctId);

    public String encodePassword(String password);

    public void modifyPassword(UserInfo user);
}
