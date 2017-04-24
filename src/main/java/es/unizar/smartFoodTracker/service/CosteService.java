package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Coste;
import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;


public interface CosteService {

    List<Coste> findByUsuario (Usuario usuario);

    void save(Coste coste);
}
