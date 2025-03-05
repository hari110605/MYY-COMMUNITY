package com.example.mycommunity.repositories;

import com.example.mycommunity.models.Complaint;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplaintRepository {

    private final JdbcTemplate jdbcTemplate;

    public ComplaintRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save a complaint to the database
    public void saveComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaints (category, subcategory, door_no, subject, description, phone_no) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            complaint.getCategory(),
            complaint.getSubcategory(),
            complaint.getDoorNo(),
            complaint.getSubject(),
            complaint.getDescription(),
            complaint.getPhoneNo()
        );
    }

    // Query to get all complaints from the database
    public List<Complaint> findAllComplaints() {
        String sql = "SELECT * FROM complaints";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Complaint complaint = new Complaint();
            complaint.setId(rs.getLong("id"));
            complaint.setCategory(rs.getString("category"));
            complaint.setSubcategory(rs.getString("subcategory"));
            complaint.setDoorNo(rs.getString("door_no"));
            complaint.setSubject(rs.getString("subject"));
            complaint.setDescription(rs.getString("description"));
            complaint.setPhoneNo(rs.getString("phone_no"));
            return complaint;
        });
    }

    // Delete a complaint by ID
    public boolean deleteComplaintById(Long id) {
        String sql = "DELETE FROM complaints WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0; // Return true if the complaint was deleted
    }
}
