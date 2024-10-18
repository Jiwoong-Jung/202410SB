package edu.du.sb1018.controller;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.service.EmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    final EmService emService;

    @GetMapping("/{deptno}/{dname}")
    public Dept index(@PathVariable Integer deptno, @PathVariable String dname) {
        return emService.updateDept(deptno, dname);
    }
}
