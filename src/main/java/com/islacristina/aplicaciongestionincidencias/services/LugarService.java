package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.model.Lugar;
import com.islacristina.aplicaciongestionincidencias.repositories.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarService {

    private final LugarRepository lugarRepository;

    @Autowired
    public LugarService(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }

    public void deleteLugar(Lugar lugar) {
        lugarRepository.delete(lugar);
    }

    public List<Lugar> getAllLugares() {
        return lugarRepository.findAll();
    }

    public Lugar saveLugar(Lugar lugar) {
        return lugarRepository.save(lugar);
    }

    // Otros métodos...
}