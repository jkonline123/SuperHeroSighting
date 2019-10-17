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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationDaoDB implements OrganizationDAO {

    @Autowired //does the injection 
    JdbcTemplate jdbc; //template for access the sql database
    
    
        private final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizations";
    
    @Override
    public Organization createOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> readAllOrganizations() {
        
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }
    

    @Override
    public Organization readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrganization(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
