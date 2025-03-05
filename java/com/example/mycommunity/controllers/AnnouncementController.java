package com.example.mycommunity.controllers;

import com.example.mycommunity.models.Announcement;
import com.example.mycommunity.services.AnnouncementService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    // Display the announcement form page
    @GetMapping("/announcementForm")
    public String showAnnouncementForm(Model model) {
        model.addAttribute("announcement", new Announcement());
        return "announcementForm";  // This should match the name of the HTML file (announcementForm.html)
    }

    // Handle the submission of the announcement form
    @PostMapping("/submit-announcement")
    public String submitAnnouncement(Announcement announcement) {
        announcementService.saveAnnouncement(announcement);
        return "redirect:/announcement-success";  // Redirect to success page after submission
    }

    // Show the success page after posting an announcement
    @RequestMapping("/announcement-success")
    public String showSuccessPage() {
        return "announcementSuccess";  // This should match the name of the success HTML file (announcementSuccess.html)
    }

    // Endpoint to fetch all announcements as JSON for viewAnnouncements page
    @GetMapping("/api/announcements")
    @ResponseBody
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();  // Fetch all announcements
    }

    // Display the viewAnnouncements page
    @GetMapping("/viewAnnouncements")
    public String viewAnnouncements() {
        return "viewAnnouncements";  // This should match the name of the HTML file (viewAnnouncements.html)
    }
}
