package com.example.demo;

import com.example.demo.dao.ISimpleBbsDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyController {

    @Autowired
    ISimpleBbsDao dao;

    @RequestMapping("/")
    public String root() throws Exception{
        // JdbcTemplate : SimpleBBS
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String userlistPage(Model model) {
        model.addAttribute("list", dao.listDao());
        return "list";
    }

    @RequestMapping("/view")
    public String view(String id, Model model) {
//        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(id));
        return "view";
    }
    
    @RequestMapping("/writeForm")
    public String writeForm() {
        
        return "writeForm";
    }
    
    @RequestMapping("/write")
    public String write(Model model, HttpServletRequest request) {
        dao.writeDao(request.getParameter("writer"),
                     request.getParameter("title"),
                     request.getParameter("content"));
        return "redirect:list";
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        dao.deleteDao(request.getParameter("id"));
        return "redirect:list";
    }

    @GetMapping("/updateForm")
    public String update(Model model, String id) {
        model.addAttribute("dto", dao.viewDao(id));
        return "updateForm";
    }

}
