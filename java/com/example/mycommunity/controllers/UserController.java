package com.example.mycommunity.controllers;

import com.example.mycommunity.models.User;
import com.example.mycommunity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // return login.html
    }

    // Display the signup page
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // return signup.html
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String handleSignup(User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "redirect:/tenant"; // redirect to tenant page after signup
    }

    // Handle login form submission
    @PostMapping("/login")
    public String handleLogin(@RequestParam String doorNo, @RequestParam String password, Model model) {
        if (userService.checkCredentials(doorNo, password)) {
            User user = userService.findUserByDoorNo(doorNo).orElse(null);
            if (user != null) {
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    return "redirect:/admin"; // redirect to admin dashboard
                } else {
                    model.addAttribute("message", "Login successful!");
                    return "tenant"; // redirect to tenant dashboard
                }
            }
        }
        model.addAttribute("error", "Invalid door number or password!");
        return "login"; // return to login page with error
    }

    @GetMapping("/tenant")
    public String tenantHome(Model model) {
        model.addAttribute("message", "Welcome to your dashboard, Tenant!");
        return "tenant"; // Load tenant.html for tenant home page
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Get session, but don't create a new one
        if (session != null) {
            session.invalidate(); // Invalidate session
        }
        return "redirect:/login"; // Redirect to the login page after logging out
    }

    // Handle redirect from complaint success page
    @GetMapping("/complaintSuccess")
    public String redirectToTenant() {
        return "redirect:/tenant"; // Redirect to tenant page
    }

    // Display admin dashboard
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        return "admin"; // Load admin.html for admin dashboard
    }

    // Display all users as JSON
    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Fetch all users from the service
    }

    // Display all users view
    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {
        return "viewUsers"; // Load viewUsers.html for viewing users
    }
}