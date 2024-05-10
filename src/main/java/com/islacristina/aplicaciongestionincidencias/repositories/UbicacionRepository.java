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

    @Query(value = "SELECT u.* FROM ubicacion u INNER JOIN lugar l ON u.id_lugar = l.id_lugar WHERE u.id_tipo_lugar = :tipoLugar AND l.nombre_lugar LIKE CONCAT('%', :nombreLugar, '%')", nativeQuery = true)
    List<Ubicacion> findByTipoLugarAndNombre(@Param("tipoLugar") Integer idTipoLugar, @Param("nombreLugar") String nombreLugar);

    Ubicacion findByTipoLugar_IdTipoLugarAndLugar_IdLugar(Integer tipoLugarId, Integer lugarId);
}
