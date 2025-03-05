package com.example.mycommunity.services;

import com.example.mycommunity.models.Announcement;
import com.example.mycommunity.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    // Save an announcement
    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    // Get all announcements
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}
