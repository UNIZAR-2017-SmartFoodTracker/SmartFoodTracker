package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }


}
