package projectspringboot.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectspringboot.library.model.Customer;
import projectspringboot.library.model.ShoppingCart;
import projectspringboot.library.service.ICustomerService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/checkout")
    public String displayMyAccountPage(Model model, Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("subTotal", shoppingCart.getTotalPrice());
        session.setAttribute("totalItem", shoppingCart.getTotalItem());
        model.addAttribute("title", "Checkout");
        return "checkout";
    }
}
