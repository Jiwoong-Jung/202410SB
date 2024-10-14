package edu.du.sb1014;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController2 {
    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/ex1")
    public void ex1(Model model) {
        for (Memo memo : memoRepository.findAll()) {
            log.info(memo);
        }
        model.addAttribute("msg", memoRepository.findById(3L));
        model.addAttribute("list", memoRepository.findAll());
    }

}
