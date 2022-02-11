package com.apirestfull.app.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestfull.app.Modelo.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

}
