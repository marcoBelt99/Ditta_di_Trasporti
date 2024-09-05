package com.beltra.sistema2.model.dao;

import com.beltra.sistema2.model.mo.Autista;
import com.beltra.sistema2.model.mo.Autobus;
import com.beltra.sistema2.model.mo.Retribuzione;
import com.beltra.sistema2.model.mo.Turno;

import java.util.List;

public interface RetribuzioneDAO {

    /** Crea la retribuzione mensile spettante ad un
     *  determinato autista e la <br>
     * inserisce sul DB */
    public void creaRetribuzione(List<Turno> turni);

    /** Restituisce la lista di retribuzioni relative ai vari mesi */
    List<Retribuzione> findAll();


}
