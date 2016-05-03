package com.cms.controller.admin;

import com.cms.entities.admin.NewsInfo;
import com.cms.entities.admin.UserInfo;
import com.cms.pagination.Page;
import com.cms.service.admin.NewsService;
import com.cms.util.Constant;
import com.cms.util.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
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
        String content = news.getContent();
        content = StringEscapeUtils.unescapeJava(content);
        content = content.replaceAll("\\\\r", "");
        content = content.replaceAll("\\\\n", "");
        content = content.replaceAll("\\\\f", "");
        content = content.replaceAll("\\\\t", "");
        content = content.replaceAll("\\\\b", "");
        content = content.replaceAll("\\\\", "");
        news.setContent(content);
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
        String content = news.getContent();
        content = StringEscapeUtils.unescapeJava(content);
        content = content.replaceAll("\\\\r", "");
        content = content.replaceAll("\\\\n", "");
        content = content.replaceAll("\\\\f", "");
        content = content.replaceAll("\\\\t", "");
        content = content.replaceAll("\\\\b", "");
        content = content.replaceAll("\\\\", "");
        news.setContent(content);
        this.newsService.updateNews(news);
        return "redirect:/news/list.do";
    }
    public static void main(String[] args) {
//       /* System.out.println("转义HTML,注意汉字:"+StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml("<ol>\\r\\n\\t<li>\\r\\n\\t\\t呃呃呃\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\t呃呃呃\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\t嗖嗖嗖\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\t咚咚咚\\r\\n\\t</li>\\r\\n</ol>")));    //转义HTML,注意汉字
//        System.out.println("反转义HTML:"+StringEscapeUtils.unescapeJava("<ol>\\r\\n\\t<li>\\r\\n\\t\\t呃呃呃\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\t点点滴滴\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\t草草草草\\r\\n\\t</li>\\r\\n\\t<li>\\r\\n\\t\\tdddd\\r\\n\\t</li>\\r\\n</ol>"));  //反转义HTML*/
        String str = "<ol>\\\\r\\\\n\\\\t<li>\\\\r\\\\n\\\\t\\\\t111\\\\r\\\\n\\\\t</li>\\\\r\\\\n\\\\t<li>\\\\r\\\\n\\\\t\\\\t2222\\\\r\\\\n\\\\t</li>\\\\r\\\\n\\\\t<li>\\\\r\\\\n\\\\t\\\\t3333\\\\r\\\\n\\\\t</li>\\\\r\\\\n\\\\t<li>\\\\r\\\\n\\\\t\\\\t4444\\\\r\\\\n\\\\t</li>\\\\r\\\\n</ol>";
        str = str.replaceAll("\\\\r", "");
        str = str.replaceAll("\\\\n", "");
        str = str.replaceAll("\\\\t", "");
        str = str.replaceAll("\\\\", "");
        System.out.println(str);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                         @RequestParam("id") Integer[] ids) {
        this.newsService.deleteNews(ids);
        return "redirect:/news/list.do";
    }
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public String detail(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                         @RequestParam("id") Integer id) {
        NewsInfo news = this.newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news/detail";
    }
}
