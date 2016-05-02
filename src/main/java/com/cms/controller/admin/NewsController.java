package com.cms.controller.admin;

import com.cms.entities.admin.NewsInfo;
import com.cms.entities.admin.UserInfo;
import com.cms.pagination.Page;
import com.cms.service.admin.NewsService;
import com.cms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evan on 2016/5/2.
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                       @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       @RequestParam(value = "title", required = false) String title) {
        Page<NewsInfo> page = new Page<NewsInfo>();
        page.setCurrentPage(currentPage);
        page.setShowCount(pageSize);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page", page);
        paramMap.put("title", title);
        page = this.newsService.queryList(paramMap);
        model.addAttribute("page", page);
        model.addAttribute("paramMap", paramMap);
        return "news/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String initAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        NewsInfo news = new NewsInfo();
        model.addAttribute("news", news);
        return "news/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                      @ModelAttribute NewsInfo news) {
        UserInfo userInfo = (UserInfo) getSession(request).getAttribute(Constant.SESSION_LOGIN_USER);
        news.setCreator(userInfo);
        news.setCreateTime(new Date());
        this.newsService.saveNews(news);
        return "redirect:/news/list.do";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String initEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                           @RequestParam("id") Integer id) {
        NewsInfo news = this.newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                       @ModelAttribute NewsInfo news) {
        this.newsService.updateNews(news);
        return "redirect:/news/list.do";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                         @RequestParam("id") Integer[] ids) {
        this.newsService.deleteNews(ids);
        return "redirect:/news/list.do";
    }
}
