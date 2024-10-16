package edu.du.sb1015_2.controller;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HeloController {
    final MyDataRepository repository;

    @GetMapping("/")
    public String index(
            @ModelAttribute("formModel") MyData mydata, Model model) {
//        model.addAttribute("msg","this is sample content.");
        List<MyData> list = repository.findAll();
        model.addAttribute("datalist",list);
        return "index";
    }

    @PostMapping("/")
    public String form(@ModelAttribute("formModel") MyData mydata) {
        repository.saveAndFlush(mydata);
        return "redirect:/";
    }

}
