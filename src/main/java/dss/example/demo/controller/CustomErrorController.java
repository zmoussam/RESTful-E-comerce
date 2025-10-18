package dss.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");

        if (statusCode == null) {
            statusCode = 500;
        }

        model.addAttribute("status", statusCode);
        model.addAttribute("message", errorMessage != null ? errorMessage : "Unexpected error occurred");
        model.addAttribute("path", requestUri != null ? requestUri : "N/A");

        if (statusCode == 404) {
            model.addAttribute("error", "Page Not Found");
        } else if (statusCode == 500) {
            model.addAttribute("error", "Internal Server Error");
        } else {
            model.addAttribute("error", "Unexpected Error");
        }

        return "error"; // templates/error.html
    }
}
