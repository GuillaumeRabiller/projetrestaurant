package com.cnam.nfa019projet.service;

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

}
