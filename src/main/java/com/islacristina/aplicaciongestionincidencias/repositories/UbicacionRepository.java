package com.islacristina.aplicaciongestionincidencias.repositories;

import com.islacristina.aplicaciongestionincidencias.model.TipoLugar;
import com.islacristina.aplicaciongestionincidencias.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {


    List<Ubicacion> findByTipoLugar(TipoLugar idTipoLugar);

    Ubicacion findByTipoLugar_IdTipoLugarAndLugar_IdLugar(Integer tipoLugarId, Integer lugarId);
}
