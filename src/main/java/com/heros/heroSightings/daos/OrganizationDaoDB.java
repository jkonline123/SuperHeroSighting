/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roshna
 */

 @Repository
public class OrganizationDaoDB implements OrganizationDAO {
     
    @Autowired //does the injection 
    JdbcTemplate jdbc; //template for access the sql database
    
        private final String INSERT_ORGANIZATION = "INSERT INTO organizations(name, description, address, contactInfo) VALUES (?,?,?,?)";
        private final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizations";
        private final String SELECT_ALL_ORGANIZATION_BY_ID = "SELECT * FROM organizations WHERE organizationID=?";//question marks are for the prepared statements
        private final String UPDATE_ORGANIZATION = "UPDATE organizations SET name = ?, description =?, address=?, contactInfo=? WHERE organizationID = ?";
        private final String DELETE_ORGANIZATION = "DELETE FROM organizations WHERE organizationID =?";
    
    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        
            jdbc.update(INSERT_ORGANIZATION,
                    organization.getName(),
                    organization.getDescription(),
                    organization.getAddress(),
                    organization.getContactInfo());
                     int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            organization.setOrganizationID(newId);
           
            return organization;
                    
    }
    @Override
    public List<Organization> readAllOrganizations() {
        
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }
    
    @Override
    public Organization readById(int id) {
        try {
            return jdbc.queryForObject(SELECT_ALL_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            
        } catch (DataAccessException ex){
            return null;
        }
    }
    @Override
    public void updateOrganization(Organization organization) {
        jdbc.update(UPDATE_ORGANIZATION, organization.getName(), organization.getDescription(), organization.getAddress(), organization.getContactInfo(), organization.getOrganizationID());
    }
    @Override
    public void deleteOrganization(int id) {
        jdbc.update(DELETE_ORGANIZATION, id);
    }
    
    public static final class OrganizationMapper implements org.springframework.jdbc.core.RowMapper<Organization> {
       @Override
       public Organization mapRow(ResultSet rs, int i) throws SQLException {
           Organization org = new Organization();
           org.setOrganizationID(rs.getInt("organizationID"));
           org.setName(rs.getString("name"));
           org.setDescription(rs.getString("description"));
           org.setAddress(rs.getString("address"));
           org.setContactInfo(rs.getString("contactInfo"));
           return org;
       }
   } 
    
    
}   

