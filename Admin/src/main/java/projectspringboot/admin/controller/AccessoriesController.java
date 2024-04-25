package projectspringboot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectspringboot.library.model.Accessories;
import projectspringboot.library.model.Category;
import projectspringboot.library.service.IAccessoriesService;
import projectspringboot.library.service.ICategoryService;
import projectspringboot.library.service.IStoreService;

import java.security.Principal;
import java.util.List;

@Controller
public class AccessoriesController {
    @Autowired
    private IAccessoriesService accessoriesService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IStoreService storeService;

    @GetMapping("/accessories")
    public String displayAccessoriesPage(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Accessories> accessoriesList = accessoriesService.findAll();
        model.addAttribute("accessories", accessoriesList);
        model.addAttribute("size", accessoriesList.size());
        model.addAttribute("title", "Accessories page");
        return "accessories/accessories";
    }

    @GetMapping("/add-accessories")
    public String displayAddNewAccessoriesPage(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("accessory", new Accessories());
        model.addAttribute("title", "Add new accessories");
        return "accessories/addNew-accessory";
    }

    @PostMapping("/add-accessories")
    public String addNewAccessory(@Validated Accessories accessories,
                                  Model model,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        try{
            if(bindingResult.hasErrors() || accessories.getImage().isEmpty()){
                if(accessories.getImage().isEmpty()){
                    redirectAttributes.addFlashAttribute("image", "Image mustn't null!!");
                }
                List<Category> categories = categoryService.findAllByActivated();
                model.addAttribute("categories", categories);
                model.addAttribute("accessories", accessories);
                return "redirect:/add-accessories";
            }
            String filename = storeService.storeFile(accessories.getImage());
            accessories.setFilename(filename);
            accessoriesService.save(accessories);
            redirectAttributes.addFlashAttribute("success", "Add new accessories success");
            return "redirect:/accessories";
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to add new accessories!!!");
            return "redirect:/add-accessories";
        }
    }
}
