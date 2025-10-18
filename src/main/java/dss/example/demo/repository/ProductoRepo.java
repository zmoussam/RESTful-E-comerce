package dss.example.demo.repository;

import dss.example.demo.model.Producto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepo extends JpaRepository<Producto, Long> {
    List<Producto> findByNameContainingIgnoreCase(String name);
}
