package com.example.mycommunity.services;

import com.example.mycommunity.models.Complaint;
import com.example.mycommunity.repositories.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public void saveComplaint(Complaint complaint) {
        complaintRepository.saveComplaint(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllComplaints();
    }

    // Calls the deleteComplaintById method in the repository
    public boolean deleteComplaint(Long id) {
        return complaintRepository.deleteComplaintById(id); // Use custom delete method here
    }
}
