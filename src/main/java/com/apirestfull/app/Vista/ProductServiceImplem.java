package com.apirestfull.app.Vista;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apirestfull.app.Modelo.Producto;
import com.apirestfull.app.Repositorio.ProductoRepositorio;
@Service
public class ProductServiceImplem implements ProductService{
	
	@Autowired
	private ProductoRepositorio productRepository;
	
	@Override
	@Transactional(readOnly= true)
	public Iterable<Producto> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Page<Producto> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly= true)
	public Optional<Producto> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return productRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
