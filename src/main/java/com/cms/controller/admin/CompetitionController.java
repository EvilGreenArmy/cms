package com.cms.controller.admin;

import com.cms.entities.admin.CompetitionInfo;
import com.cms.entities.admin.ProductInfo;
import com.cms.entities.admin.UserInfo;
import com.cms.pagination.Page;
import com.cms.service.admin.CompetitionService;
import com.cms.service.admin.UserService;
import com.cms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evan on 2016/4/3.
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends BaseController {
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                       @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<CompetitionInfo> page = new Page<CompetitionInfo>();
        page.setCurrentPage(currentPage);
        page.setShowCount(pageSize);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page", page);
        UserInfo userInfo = (UserInfo) getSession(request).getAttribute(Constant.SESSION_LOGIN_USER);
        int isAdmin = 1;
        if (!userService.isAdmin(userInfo.getId())) {
            paramMap.put("acctId", userInfo.getId());
            isAdmin = 0;
        }
        page = this.competitionService.queryList(paramMap);
        model.addAttribute("page", page);
        model.addAttribute("isAdmin", isAdmin);
        return "competition/list";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public
    @ResponseBody
    String add(HttpServletRequest request, HttpServletResponse response, ModelMap model,
               @RequestParam(value = "productId") Integer productId,
               @RequestParam(value = "comValue") BigDecimal comValue) {
        try {
            CompetitionInfo competition = new CompetitionInfo();
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(productId);
            competition.setProductInfo(productInfo);
            UserInfo userInfo = (UserInfo) getSession(request).getAttribute(Constant.SESSION_LOGIN_USER);
            competition.setUserInfo(userInfo);
            competition.setCreateTime(new Date());
            competition.setComValue(comValue);
            competition.setStatus("1");
            this.competitionService.saveCompetition(competition);
            return "{\"state\":\"1\"}";
        } catch (Exception e) {

            return "{\"state\":\"0\"}";

        }
    }

   @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                         @RequestParam("id") Integer[] ids) {
        this.competitionService.deleteCompetition(ids);
        return "redirect:/competition/list.do";
    }
}
