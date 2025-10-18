package dss.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DatabaseExportService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseExportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public byte[] exportDatabaseToSql() {
        StringBuilder sb = new StringBuilder();

        sb.append("-- SQL Export of PRODUCTO table\n");
        sb.append("BEGIN TRANSACTION;\n\n");

        jdbcTemplate.query("SELECT * FROM PRODUCTO", rs -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name").replace("'", "''"); 
            Double price = rs.getDouble("price");
            sb.append("INSERT INTO PRODUCTO(id, name, price) VALUES(")
              .append(id).append(", '").append(name).append("', ").append(price).append(");\n");
        });

        sb.append("\nCOMMIT;\n");
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
