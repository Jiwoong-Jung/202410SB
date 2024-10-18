package com.study.emjpa.controller;

import com.study.emjpa.entity.Cart;
import com.study.emjpa.entity.Product;
import com.study.emjpa.repository.CartRepository;
import com.study.emjpa.repository.MyEntityManager;
import com.study.emjpa.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyEntityManager myem;
    private final CartRepository cartRepository;


    @GetMapping("/person")
    @ResponseBody
    public Person insertPerson() {
        Person person = myem.personCreate();
        return person;
    }

    @GetMapping("/create")
    @ResponseBody
    public String create() {
        String str = myem.create();
        return str;
    }

    @GetMapping("/update")
    @ResponseBody
    public String update() {
        String str = myem.update();
        return str;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete() {
        String str = myem.delete();
        return str;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Person> list() {
        List<Person> list = myem.selectAll();
        return list;
    }

    @GetMapping("/cart")
    @ResponseBody
    public String cart() {
        String str = myem.putInCart();
        return str;
    }

    @GetMapping("/getcart")
    @ResponseBody
    public Cart getCart() {
        Cart cart = myem.getCart();
        return cart;
    }

    @GetMapping("/")
    public String index() {
        return "view/index";
    }

    @GetMapping("/page2")
    public String listAction(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        //List<Notice> list = noticeRepository.findAll();
        List<Cart> list = cartRepository.findAllByOrderByIdDesc();
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<Cart> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        model.addAttribute("list", page);
        return "view/list";
    }

    @GetMapping("/detail")
    public String detail(Long id, Model model) {
        Cart cart = myem.getCart2(id);
        System.out.println("===>"+cart.getProducts());
        model.addAttribute("list", cart.getProducts());
        return "view/detail";
    }

    @GetMapping("/insert1")
    public String insert1() {
        String str = myem.putInCart();
        System.out.println("===>"+str);
        return "redirect:/page2";
    }
}
