package dss.example.demo.service;

import dss.example.demo.model.CartModel;
import dss.example.demo.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartModel cart;

    public CartService() {
        this.cart = new CartModel();
    }

    public CartModel getCart() {
        return cart;
    }

    public void addProduct(Producto producto) {
        cart.addItem(producto);
    }

    public void removeProduct(Long id) {
        cart.removeItemById(id);
    }

    public int getCartSize() {
        return cart.size();
    }
}
