package com.cnam.nfa019projet.service;

import com.cnam.nfa019projet.form.FactureNote;
import com.cnam.nfa019projet.form.ListePlatFacture;
import com.cnam.nfa019projet.form.ShowPlatNote;
import com.cnam.nfa019projet.form.UpdateNoteForm;
import com.cnam.nfa019projet.model.Note;
import com.cnam.nfa019projet.model.Plat;
import com.cnam.nfa019projet.model.Table;
import com.cnam.nfa019projet.repository.NoteRepository;
import com.cnam.nfa019projet.repository.PlatRepository;
import com.cnam.nfa019projet.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional
public class NoteService {

    @Autowired
    NoteRepository noteRepository ;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    PlatRepository platRepository;

    @Autowired
    UtilisateurService utilisateurService ;


    public Note changeNote(UpdateNoteForm aNote){
        Note note = noteRepository.findById(aNote.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid note id"));
        note.setCouvert(aNote.getNbCouvert());
        Optional<Table> table = tableRepository.findById(aNote.getTableId());
        table.ifPresent(newTable -> {
            note.changeTable(newTable);
        });
        return note ;
    }


    //Méthode pour regrouper les plats avec une quantité au sein d'une même note

    public Map<Long, Integer> triPlat(long idNote){
        Map<Long, Integer> map = new HashMap<>();
        //on ressort une liste avec les id de chaque plat
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note id"));
        List<Plat> plats = aNote.getPlats();
        List<Long> idPlats = new ArrayList<>();
        for (Plat plat:plats) {
            idPlats.add(plat.getId());
        }
        //à partir de cette liste on remplit la map (la clé est l'id du plat, la valeur la quantité au sein d'une même note
        for (int i = 0; i<idPlats.size(); i++) {
            //si l'élément existe déjà, on augmente sa valeur
            if(map.containsKey(idPlats.get(i))){
                map.replace(idPlats.get(i), map.get(idPlats.get(i))+1);
            }
            //sinon on l'ajoute à la map
            else {
                map.put(idPlats.get(i), 1);
            }
        }
        return map;
    }

    //Méthode pour envoyer liste de plats à listePlatNote

    public List<ShowPlatNote> envoiListePlat(long idNote){
        Map<Long, Integer> platNote = triPlat(idNote);
        List<ShowPlatNote> listePlat = new ArrayList<>();
        for (Long key : platNote.keySet()){
            Plat plat = platRepository.findById(key).orElseThrow(() -> new IllegalArgumentException("Invalid plat id"));
            ShowPlatNote aPlat = new ShowPlatNote();
            aPlat.setNomCategorie(plat.getCategorie().getNomCategorie());
            aPlat.setDescription(plat.getDescription());
            aPlat.setPrix(plat.getPrix());
            aPlat.setQuantite(platNote.get(key));
            aPlat.setIdPlat(key);
            listePlat.add(aPlat);
        }
        return listePlat;
    }


    //Méthode pour remplir le form FactureNote pour l'édition d'une facture

    public FactureNote facturer(Note aNote){
        FactureNote facture = new FactureNote();
        facture.setNoTable(aNote.getTable().getNoTable());
        facture.setNbCouvert(aNote.getCouvert());
        facture.setServeur(utilisateurService.getNomUser());
        facture.setPlats(listePlat(aNote));
        facture.setIdNote(aNote.getId());

        //Récupération de la somme TTC, et différentes valeurs de TVA
        BigDecimal sommeTTC = new BigDecimal("0");
        BigDecimal TVA10 = new BigDecimal("0");
        BigDecimal TVA20 = new BigDecimal("0");
        for (ListePlatFacture plat : facture.getPlats()) {
            BigDecimal prixPlat = new BigDecimal(Float.toString(plat.getPrix()));
            sommeTTC = sommeTTC.add(prixPlat);

            if (plat.getTva() == 10) {          //plat avec TVA à 10%
                TVA10 = TVA10.add(prixPlat.multiply(BigDecimal.valueOf(plat.getTva() / 100))) ;
                TVA10 = TVA10.setScale(2, RoundingMode.HALF_UP);
            } else {       //plat avec TVA à 20%
                TVA20 = TVA20.add(prixPlat.multiply(BigDecimal.valueOf(plat.getTva() / 100))) ;
                TVA20 = TVA20.setScale(2, RoundingMode.HALF_UP);
            }
        }
        //on renseigne la sommeHT
        BigDecimal sommeHT = sommeTTC.subtract(TVA10).subtract(TVA20) ;
        sommeHT =sommeHT.setScale(2, RoundingMode.HALF_UP);

        facture.setSommeTTC(sommeTTC);
        facture.setSommeHT(sommeHT);
        facture.setTVA10(TVA10);
        facture.setTVA20(TVA20);

        return facture ;
    }


    //Méthode pour renseigner le form ListePlatFacture (utilisé pour remplir le form FactureNote)

    public List<ListePlatFacture> listePlat(Note aNote){
        Map<Long, Integer> platNote = triPlat(aNote.getId());
        List<ListePlatFacture> listePlat = new ArrayList<>();
        for (Long key : platNote.keySet()){
            Plat plat = platRepository.findById(key).orElseThrow(() -> new IllegalArgumentException("Invalid plat id"));
            ListePlatFacture aPlat = new ListePlatFacture();
            aPlat.setDescription(plat.getDescription());
            aPlat.setQuantite(platNote.get(key));
            aPlat.setTva(plat.getCategorie().getTva());
            //On multiplie le prix d'un plat par sa qté qui vient juste d'être renseignée
            aPlat.setPrix(plat.getPrix()*aPlat.getQuantite());
            listePlat.add(aPlat);
        }

        return listePlat;
    }


    //Méthode pour renvoyer le nb de commandes en cours à l'index serveur
    public int nbNoteEnCours(){
        return noteRepository.findAllByReglementFalse().size();
    }


    //Méthode pour renvoyer le nb de tables dispo à l'index serveur

    public int nbTableDispo(){
        return (tableRepository.findAll().size() - nbNoteEnCours());
    }





}
