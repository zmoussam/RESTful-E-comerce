package dss.example.demo.controller;

import dss.example.demo.model.Producto;
import dss.example.demo.repository.ProductoRepo;
import dss.example.demo.service.DatabaseExportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private DatabaseExportService databaseExportService;

    @Autowired
    private ProductoRepo productRepo;

    @GetMapping("/admin/download-db-sql")
    public ResponseEntity<byte[]> downloadDatabase() {
        byte[] sqlBytes = databaseExportService.exportDatabaseToSql();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=database.sql")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(sqlBytes);
    }

    @GetMapping("/admin")
    public String adminPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Producto> productos;

        if (keyword != null && !keyword.isEmpty()) {
            productos = productRepo.findByNameContainingIgnoreCase(keyword);
        } else {
            productos = productRepo.findAll();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("keyword", keyword); // keep the search term in the input
        return "admin";
    }
}
