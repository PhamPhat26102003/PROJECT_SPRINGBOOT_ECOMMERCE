package projectspringboot.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectspringboot.library.model.Accessories;
import projectspringboot.library.service.IAccessoriesService;

import java.util.List;

@Controller
public class AccessoriesController {

    @Autowired
    private IAccessoriesService accessoriesService;

    @GetMapping("/list-accessory")
    public String displayListAccessoriesPage(Model model){
        List<Accessories> accessories = accessoriesService.findAllAccessoriesByActivated();
        model.addAttribute("accessories", accessories);
        model.addAttribute("title", "List Accessories");
        return "accessories/list-accessory";
    }
}
