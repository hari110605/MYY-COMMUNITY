package com.example.mycommunity.controllers;

import com.example.mycommunity.models.Complaint;
import com.example.mycommunity.services.ComplaintService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/tenant/complaint")
    public String showComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaint";  // This should match the name of the HTML file (complaint.html)
    }

    @PostMapping("/submit-complaint")
    public String submitComplaint(Complaint complaint) {
        complaintService.saveComplaint(complaint);
        return "redirect:/complaint-success";  // Redirect to success page
    }

    @RequestMapping("/complaint-success")
    public String showSuccessPage() {
        return "complaint_success";  // This should match the name of the success HTML file (complaint_success.html)
    }

    // Endpoint to fetch all complaints as JSON for viewComplaints page
    @GetMapping("/api/complaints")
    @ResponseBody
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints(); // Fetch all complaints from the service
    }

    // Endpoint to delete a complaint by ID when "Completed" button is clicked
    @DeleteMapping("/api/complaints/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
        boolean deleted = complaintService.deleteComplaint(id); // Call service delete method
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if deletion is successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if complaint doesn't exist
        }
    }
    // Return the viewComplaints page
    @GetMapping("/viewComplaints")
    public String viewComplaints(Model model) {
        return "viewComplaints"; // Return the viewComplaints.html
    }
}
