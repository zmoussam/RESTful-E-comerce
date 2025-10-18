package dss.example.demo.controller;

import dss.example.demo.model.Producto;
import dss.example.demo.service.CartService;
import dss.example.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cart")
public class CartController {

    private final ProductoService productService;
    private final CartService cartService;

    public CartController(ProductoService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public CartService cart() {
        return cartService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartItems", cartService.getCart().getItems());
        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, RedirectAttributes ra) {
        Producto p = productService.getProductById(id);
        if (p != null) {
            cartService.addProduct(p);
            ra.addFlashAttribute("msg", "Añadido al carrito");
        }
        return "redirect:/productos";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, RedirectAttributes ra) {
        cartService.removeProduct(id);
        ra.addFlashAttribute("msg", "Eliminado del carrito");
        return "redirect:/cart";
    }
}
