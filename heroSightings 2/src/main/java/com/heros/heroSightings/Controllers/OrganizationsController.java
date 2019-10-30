/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.OrganizationDAO;
import com.heros.heroSightings.dtos.Organization;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Roshna
 */
@Controller
public class OrganizationsController {

    @Autowired//does dependency injection
    OrganizationDAO dao;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = dao.readAllOrganizations();//controller is getting parameters from template
        model.addAttribute("organization", organizations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String contactInfo = request.getParameter("contactInfo");

        Organization organization = new Organization();

        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setContactInfo(contactInfo);

        dao.createOrganization(organization);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {

        dao.deleteOrganization(id);

        return "redirect:/organizations";
    }

    
    
    
    @GetMapping("editOrganizations")
    public String editOrganizations(Integer id, Model model) {
        
        Organization organization = dao.readById(id);

        model.addAttribute("organization", organization);

        return "editOrganizations";
    }

    
    
    
    @PostMapping("editOrganizations")
    public String performEditOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = dao.readById(id);

        organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
        organization.setAddress(request.getParameter("address"));

        organization.setContactInfo(request.getParameter("contactInfo"));

        dao.updateOrganization(organization);

        return "redirect:/organizations";

    }
}
