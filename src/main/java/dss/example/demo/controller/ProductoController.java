package dss.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dss.example.demo.model.Producto;
import dss.example.demo.service.ProductoService;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productService;

    @GetMapping
    public String viewProductos(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Producto> productos = productService.searchProducts(search);
        model.addAttribute("products", productos);
        model.addAttribute("search", search);
        return "productos";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Producto());
        model.addAttribute("productos", productService.getAllProducts());
        return "formulario-producto"; // templates/product_form.html
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name, @RequestParam double price) {
        Producto product = new Producto();
        product.setName(name);
        product.setPrice(price);
        productService.saveProduct(product);
        return "redirect:/admin"; // Redirigir correctamente a /admin
    }

    // Show edit page for a specific product
    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable Long id, Model model) {
        Producto producto = productService.getProductById(id);
        if (producto == null) {
            return "redirect:/productos/add"; // fallback if product doesn't exist
        }
        model.addAttribute("producto", producto); // send product to template
        return "edit-producto"; // new template specifically for editing
    }

    @PostMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam double price) {

        productService.updateProduct(id, name, price);
        return "redirect:/productos/add"; 
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes ra) {
        productService.deleteProduct(id);
        ra.addFlashAttribute("msg", "Eliminado del sistema");
        return "redirect:/productos/add"; // Redirigir correctamente a /admin
    }
}