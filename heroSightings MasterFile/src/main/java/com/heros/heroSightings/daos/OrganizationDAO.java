/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Organization;
import java.util.List;

/**
 *
 * @author triplexlj
 */
public interface OrganizationDAO {
    
    
    //CREATE
    
    
    Organization createOrganization(Organization organization);
    
    //ReadAll
    
    List<Organization> readAllOrganizations();
    
    //readById
    
    Organization readById(int id);
    
    //Update
    
    void updateOrganization(Organization o);
    
    //Delete
    
    void deleteOrganization(int id);
    
    
}
