package edu.du.sb1015.controller;

import edu.du.sb1015.dto.DataObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController {

//    @GetMapping("/")
    public String helo(Model model) {
        model.addAttribute("msg", "이름을 적어 주세요");
        return "index";
    }

    @PostMapping("/")
    public String heloPost(@RequestParam("text1") String name, Model model) {
        model.addAttribute("value", name);
        model.addAttribute("msg", "당신의 이름은 "+name+"이군요!");
        return "index";
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg","message 1<hr/>message 2<br/>message 3");
        DataObject obj = new DataObject(123, "lee","lee@flower");
        model.addAttribute("object",obj);
        return "index";
    }
    @GetMapping("/id/{id}")
    public String index(@PathVariable int id, Model mav) {
        mav.addAttribute("id",id);
        mav.addAttribute("check",id >= 0);
        mav.addAttribute("trueVal","POSITIVE!");
        mav.addAttribute("falseVal","negative...");
        DataObject obj = new DataObject(123, "lee","lee@flower");
        mav.addAttribute("object",obj);
        return "index";
    }

    @RequestMapping("/month/{month}")
    public ModelAndView index(@PathVariable int month, ModelAndView mav) {
        mav.setViewName("index2");
        int m = Math.abs(month) % 12;
        m = m == 0 ? 12 : m;
        mav.addObject("month",m);
        mav.addObject("check",Math.floor(m / 3));
        return mav;
    }

}
