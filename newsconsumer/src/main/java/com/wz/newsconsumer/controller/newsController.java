package com.wz.newsconsumer.controller;

import com.wz.common.pojo.news;
import com.wz.common.service.newsService;
import com.wz.common.utils.PageBean;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class newsController {
    @Reference
    private newsService newsService;

    @RequestMapping("/")
    public String toindex(){
        return "index.html";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        String currPage = request.getParameter("currPage");
        int currentPage = 1;
        if(null != currPage || !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        int pageSize = 2;

        List<news> newslist = newsService.findItemByPage(currentPage, pageSize);
        PageBean<news> page = newsService.Pages(currentPage, pageSize);
        result.put("newslist",newslist);
        result.put("page",page);
        return result;
    }
    @RequestMapping("/toadd")
    public String toadd(){
        return "add.html";
    }

    @RequestMapping("/add")
    public String add(news news){
        System.out.println(1);
        int flag = newsService.addNews(news);
        return "index.html";
    }
    @RequestMapping("/tomodify")
    public String tomodify(){
        return "modify.html";
    }

    @RequestMapping("/modify")
    public String modify(news news){
        int flag = newsService.modifyNews(news);
        return "index.html";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        Long newId = Long.parseLong(id);
        int flag = newsService.deleteNews(newId);
        if(flag>0){
            return "index.html";
        }else{
            return "error.html";
        }

    }

     @RequestMapping("/newsPage")
      @ResponseBody
     public List<news> itemsPage(int currentPage,int pageSize){
                 return newsService.findItemByPage(currentPage, pageSize);
             }

}
