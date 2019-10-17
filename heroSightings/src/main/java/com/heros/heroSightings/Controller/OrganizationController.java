/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controller;

import com.heros.heroSightings.daos.OrganizationDAO;
import com.heros.heroSightings.dtos.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author triplexlj
 */
@Controller//let Spring know its controller for thyme leaf to run smoothly
public class OrganizationController {

    @Autowired//does dependency injection
    OrganizationDAO dao;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = dao.readAllOrganizations();

        model.addAttribute("organization", organizations);

        return "organizations";

    }

}
