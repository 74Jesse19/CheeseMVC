package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseType;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired // this says i should be given an instance of this class by
    // the framework uses concept called dependency injection
    private CheeseDao cheeseDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses",cheeseDao.findAll());
        model.addAttribute("title", "Cheeses");


        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        model.addAttribute(new Cheese());
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("cheeseTypes", CheeseType.values());
        Cheese c = cheeseDao.findOne(cheeseId);

        model.addAttribute("cheeses", c);
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, String name, String description, @ModelAttribute @Valid Cheese cheese,
                                  Errors errors, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            return "cheese/edit";
        }
        model.addAttribute("cheese", cheese);
        Cheese c = cheeseDao.findOne(cheeseId);

        c.setName(name);
        c.setDescription(description);
        model.addAttribute("cheeses", c);


        return "redirect:";
    }

}
