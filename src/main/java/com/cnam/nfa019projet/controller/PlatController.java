package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.model.CategoriePlat;
import com.cnam.nfa019projet.model.Plat;
import com.cnam.nfa019projet.repository.CategoriePlatRepository;
import com.cnam.nfa019projet.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PlatController {

    //Creation du Repository
    @Autowired
    private PlatRepository platRepository ;

    @Autowired
    private CategoriePlatRepository categoriePlatRepository ;

    /**
     * LISTE DES PLATS EN BASE DE DONNEE
     *
     * READ
     *
     */

    @GetMapping("/readPlat")
    public String readPlat(Model model) {
        List<Plat> platList = platRepository.findAll();
        model.addAttribute("platList", platList);
        return "/Plat/readPlat";
    }

    /**
     * FORMULAIRE DE SAISIE D'UN NOUVEAU PLAT
     *
     * CREATE
     *
     */

    @RequestMapping(value = {"/createPlat"}, method = RequestMethod.GET)
    public String createPlat(Model model) {
        Plat aPlat = new Plat();
        model.addAttribute("aPlat", aPlat);
        List<CategoriePlat> categorieList = categoriePlatRepository.findAll();
        model.addAttribute("categorieList", categorieList);
        return "/Plat/createPlat";
    }

    /**
     * SAUVEGARDE FORMULAIRE NOUVEAU PLAT
     *
     * SAVE
     *
     */

    @RequestMapping(value = {"/savePlat"}, method = RequestMethod.POST)
    public String savePlat(@ModelAttribute("aPlat") Plat aPlat, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || aPlat == null) {
            return "/error";
        } else {
            //Récupération de la catégorie
            Optional<CategoriePlat> cat = categoriePlatRepository.findById(Long.valueOf(aPlat.getCategorieId()));
            cat.ifPresent(categorie -> {
                categorie.addPlats(aPlat);
            });

            platRepository.save(aPlat);
            return "redirect:readPlat";
        }
    }

    /**
     * PAGE DE UPDATE PLAT VIA ID
     *
     * UPDATE
     *
     */

    @GetMapping("/updatePlat/{id}")
    public String updatePlatFormulaire(@PathVariable("id") long id, Model model) {
        Plat aPlat = platRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plat id:" + id));
        model.addAttribute("aPlat", aPlat);
        return "/Plat/updatePlat";
    }

    @PostMapping("/updatePlat")
    public String updatePlat(@Valid Plat aPlat, BindingResult result) {
        if (result.hasErrors() || aPlat == null) {
            return "/error";
        }
        platRepository.save(aPlat);
        return "redirect:readPlat";
    }

    /**
     * PAGE DE SUPPRESSION PLAT VIA ID
     *
     * DELETE
     *
     */

    @GetMapping("/verifDeletePlat/{id}")
    public String verifDeletePlat(@PathVariable("id") long id, Model model) {
        Plat aPlat = platRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + id));
        boolean supr ;
        //Vérification si le plat est alloué à certaines notes (donc suppression possible ou non)
        if(aPlat.getNotes().isEmpty()){
            supr = true;
        } else {
            supr = false ;
        }
        model.addAttribute("aPlat", aPlat);
        model.addAttribute("supr", supr);
        return "/Plat/deletePlat";
    }

    @GetMapping("/deletePlat/{id}")
    public String deletePlat(@PathVariable("id") long id, Model model) {
        Plat aPlat = platRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + id));
        if(aPlat != null) {
            aPlat.getCategorie().deletePlat(aPlat);
            platRepository.delete(aPlat);
            List<Plat> platList = platRepository.findAll();
            model.addAttribute("platList", platList);
            return "/Plat/readPlat";
        } else {
            return "/error";
        }
    }


}


