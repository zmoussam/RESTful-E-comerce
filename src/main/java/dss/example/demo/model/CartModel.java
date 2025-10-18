package dss.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private final List<Producto> items = new ArrayList<>();

    public List<Producto> getItems() {
        return items;
    }

    public void addItem(Producto producto) {
        items.add(producto);
    }

    public void removeItemById(Long id) {
        items.removeIf(p -> p.getId().equals(id));
    }

    public void clear() {
        items.clear();
    }

    public int size() {
        return items.size();
    }
}
