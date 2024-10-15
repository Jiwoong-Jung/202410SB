package edu.du.sb1015.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HeloController {

    @GetMapping("/")
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
}
