package dss.example.demo; // <-- this is your base package

import dss.example.demo.model.Producto;
import dss.example.demo.repository.ProductoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Pr1Application {

    public static void main(String[] args) {
        SpringApplication.run(Pr1Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ProductoRepo productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Producto("Wireless Mouse", 19.99));
                productRepository.save(new Producto("Mechanical Keyboard", 59.99));
                productRepository.save(new Producto("USB-C Charger", 24.99));
                productRepository.save(new Producto("Bluetooth Headphones", 79.99));
                productRepository.save(new Producto("External SSD 1TB", 119.99));
                productRepository.save(new Producto("27-inch Monitor", 189.99));
                productRepository.save(new Producto("Webcam HD", 39.99));
                productRepository.save(new Producto("Gaming Chair", 249.99));
            }
        };
    }
}
