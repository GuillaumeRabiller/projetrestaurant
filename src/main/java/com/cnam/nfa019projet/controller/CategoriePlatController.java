package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.model.CategoriePlat;
import com.cnam.nfa019projet.repository.CategoriePlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoriePlatController {

    //Creation du Repository
    @Autowired
    private CategoriePlatRepository categoriePlatRepository ;

    /**
     * LISTE DES CATEGORIES PLAT EN BASE DE DONNEE
     *
     * READ
     *
     */

    @GetMapping("/readCategoriePlat")
    public String readCategoriePlat(Model model) {
        List<CategoriePlat> categorieList = categoriePlatRepository.findAll();
        model.addAttribute("categorieList", categorieList);
        return "/CategoriePlat/readCategoriePlat";
    }

    /**
     * FORMULAIRE DE SAISIE D'UNE NOUVELLE CATEGORIE PLAT
     *
     * CREATE
     *
     */

    @RequestMapping(value = {"/createCategoriePlat"}, method = RequestMethod.GET)
    public String createCategoriePlat(Model model) {
        CategoriePlat aCategorie = new CategoriePlat();
        model.addAttribute("aCategorie", aCategorie);
        return "/CategoriePlat/createCategoriePlat";
    }

    /**
     * SAUVEGARDE FORMULAIRE NOUVELLE CATEGORIE PLAT
     *
     * SAVE
     *
     */

    @RequestMapping(value = {"/saveCategoriePlat"}, method = RequestMethod.POST)
    public String saveCategoriePlat(@ModelAttribute("aCategorie") CategoriePlat aCategorie, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || aCategorie == null) {
            return "/error";
        } else {
            categoriePlatRepository.save(aCategorie);
            return "redirect:readCategoriePlat";
        }
    }

    /**
     * PAGE DE UPDATE CATEGORIE PLAT VIA ID
     *
     * UPDATE
     *
     */

    @GetMapping("/updateCategoriePlat/{id}")
    public String updateCategoriePlatFormulaire(@PathVariable("id") long id, Model model) {
        CategoriePlat aCategorie = categoriePlatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categorie id:" + id));
        model.addAttribute("aCategorie", aCategorie);
        return "/CategoriePlat/updateCategoriePlat";
    }

    @PostMapping("/updateCategoriePlat")
    public String updateCategoriePlat(@Valid CategoriePlat aCategorie, BindingResult result) {
        if (result.hasErrors() || aCategorie == null) {
            return "/error";
        }
        categoriePlatRepository.save(aCategorie);
        return "redirect:readCategoriePlat";
    }

    /**
     * PAGE DE SUPPRESSION CATEGORIE PLAT VIA ID
     *
     * DELETE
     *
     */

    @GetMapping("/verifDeleteCategoriePlat/{id}")
    public String verifDeleteCategoriePlat(@PathVariable("id") long id, Model model) {
        CategoriePlat aCategorie = categoriePlatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categorie Id:" + id));
        boolean supr ;
        //Vérification si la catégorie est allouée à certains plats (donc suppression possible ou non)
        if(aCategorie.getPlats().isEmpty()){
            supr = true;
        } else {
            supr = false ;
        }
        model.addAttribute("aCategorie", aCategorie);
        model.addAttribute("supr", supr);
        return "/CategoriePlat/deleteCategoriePlat";
    }

    @GetMapping("/deleteCategoriePlat/{id}")
    public String deleteCategoriePlat(@PathVariable("id") long id, Model model) {
        CategoriePlat aCategorie = categoriePlatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categorie Id:" + id));
        if(aCategorie != null) {
            categoriePlatRepository.delete(aCategorie);
            List<CategoriePlat> categorieList = categoriePlatRepository.findAll();
            model.addAttribute("categorieList", categorieList);
            return "/CategoriePlat/readCategoriePlat";
        } else {
            return "/error";
        }
    }


}
