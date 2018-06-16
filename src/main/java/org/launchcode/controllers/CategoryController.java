package org.launchcode.controllers;


import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "", method= RequestMethod.GET)
    public String index(Model model) {

        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + categoryDao.findAll());
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("title", "Categories");
        return "category/index";
    }

}