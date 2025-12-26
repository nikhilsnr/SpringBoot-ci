package com.example.git.login;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @GetMapping("/loginString")
    public @ResponseBody String showLogin() {
        return "login";
    }

    @PostMapping("/loginSuccess")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

    	
        // Dummy authentication
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/welcome";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
        
    }

    @GetMapping("/welcome")
    public String welcomePage(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        System.out.println("Inside Welcome");
        
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        return "welcome";
    }
}
