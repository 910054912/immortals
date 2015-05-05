package com.king.controller;

import com.king.dao.DaoTemplate;
import com.king.vo.PersonVO;
import com.king.vo.ScheduleVO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Peabody on 2015/3/19.
 */
@Controller
@RequestMapping("/first")
public class CircleController {
    @Autowired
    private DaoTemplate daoTemplate;//可以这么用，即自动装配的字段不一定必须是接口

    @RequestMapping("savePerson.do")
    public String showMsg(HttpServletRequest request,Model model){
        PersonVO personVO = new PersonVO();
        personVO.setAge(21);
        personVO.setName("Kitty");
        personVO.setSex(1);

        PersonVO vo2 = new PersonVO();
        vo2.setAge(99);
        vo2.setName("Kitty");
        vo2.setSex(1);

        List list = new ArrayList();
        list.add(personVO);
        list.add(vo2);
        daoTemplate.batchSaveOrUpdate(list);
        model.addAttribute("msg",":right!You got me!");
        return "/showMsg";
    }
}
