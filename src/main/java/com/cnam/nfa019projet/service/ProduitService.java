package com.cnam.nfa019projet.service;

import com.cnam.nfa019projet.model.Categorie;
import com.cnam.nfa019projet.model.Produit;
import com.cnam.nfa019projet.repository.CategorieRepository;
import com.cnam.nfa019projet.repository.ProduitRepository;
import com.cnam.nfa019projet.form.UpdateProduitForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class ProduitService {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    CategorieRepository categorieRepository ;

    @Autowired
    ProduitRepository produitRepository ;

    public Produit createProd(Produit aProduit){
        aProduit.setDateCreation(LocalDate.now());
        aProduit.setUtilisateur(utilisateurService.getNomUser());
        //Recupération de la categorie
        Optional<Categorie> cat = categorieRepository.findById(Long.valueOf(aProduit.getCategorieId()));
        cat.ifPresent(categorie -> {
            categorie.addProduit(aProduit);
        });
        return aProduit ;
    }

    public Produit changeProd(UpdateProduitForm aProduit){
        Produit produit = produitRepository.findById(aProduit.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid produit id"));
        //Recupération de la categorie
        Optional<Categorie> cat = categorieRepository.findById(aProduit.getCategorieId());
        cat.ifPresent(categorie -> {
            produit.changeCategorie(categorie);
        });
        produit.setNomProduit(aProduit.getNomProduit());
        produit.setDureeConservation(aProduit.getDureeConservation());
        return produit ;
    }

}
