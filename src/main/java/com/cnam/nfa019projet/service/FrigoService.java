package com.cnam.nfa019projet.service;

import com.cnam.nfa019projet.form.TempDuJour;
import com.cnam.nfa019projet.model.Frigo;
import com.cnam.nfa019projet.model.ReleveTemp;
import com.cnam.nfa019projet.repository.FrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FrigoService {

    @Autowired
    private FrigoRepository frigoRepository ;


    /*
     *  METHODE PERMETTANT DE RECUPERER, POUR CHAQUE FRIGO, LES TEMPERATURES RELEVEES LE JOUR MEME,
     * CELLE DU MATIN, ET CELLE DE L'APRES-MIDI
     *
     */

    public List<TempDuJour> lastTempList() {
        List<Frigo> frigoList = frigoRepository.findAll() ;
        List<TempDuJour> tempList = new ArrayList<>();
        for (Frigo frigo : frigoList) {
            TempDuJour releve = new TempDuJour();
            releve.setNomFrigo(frigo.getNomFrigo());
            List<ReleveTemp> releveTemp = frigo.getRelevesTemp();
            //on va chercher les tempéaratures du matin et après midi
            for (ReleveTemp temp:releveTemp) {
                if (temp.getDateEnregTemp().toLocalDate().isEqual(LocalDate.now())){
                    if (temp.getDateEnregTemp().toLocalTime().isBefore(LocalTime.of(12,0))){
                        releve.setTempMatin(temp.getTemperature());
                    } else {
                        releve.setTempAprem(temp.getTemperature());
                    }
                }
            }
            //en fonction des températures relevées, on met à jour les boolean pour savoir si elles dépassent ou non les seuils de temp du frigo
            if(releve.getTempMatin() != -100 && releve.getTempMatin() < frigo.getTempMini()){
                releve.setTempMatinDown(true);
            }
            if(releve.getTempMatin() != -100 && releve.getTempMatin() > frigo.getTempMaxi()){
                releve.setTempMatinUp(true);
            }
            if(releve.getTempAprem() != -100 && releve.getTempAprem() < frigo.getTempMini()){
                releve.setTempApremDown(true);
            }
            if(releve.getTempAprem() != -100 && releve.getTempAprem() > frigo.getTempMaxi()){
                releve.setTempApremUp(true);
            }

            tempList.add(releve);
        }
        return tempList ;
    }

}
