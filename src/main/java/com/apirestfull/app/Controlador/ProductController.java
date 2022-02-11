package com.apirestfull.app.Controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestfull.app.Modelo.Producto;
import com.apirestfull.app.Vista.ProductService;



@RestController
@RequestMapping("/api/productos")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//CREAR PRODUCTO
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Producto producto){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(producto));
	}
	
	//LISTAR PRODUCTO
	@GetMapping("/{codigo}")
	public ResponseEntity<?> read (@PathVariable Long codigo){
		Optional<Producto> oProduct = productService.findById(codigo);
		
		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oProduct);
	}
	
	//ACTUALIZAR PRODUCTO
	@PutMapping("/{codigo}")
	public ResponseEntity<?> update (@RequestBody Producto productDetail, @PathVariable Long codigo){
		Optional<Producto> oProduct = productService.findById(codigo);
		
		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		oProduct.get().setDescripcion(productDetail.getDescripcion());
		oProduct.get().setPrecio(productDetail.getPrecio());
		oProduct.get().setCantidad(productDetail.getCantidad());
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDetail));
	}
	
	//ELIMINAR UN USUARIO
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long codigo){
		
		if(!productService.findById(codigo).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		productService.deleteById(codigo);
		return ResponseEntity.ok().build();
	}
	
	//LEER TODOS LOS USUARIOS
		@GetMapping
		public List<Producto> readAll () {
			List<Producto> products = StreamSupport
					.stream(productService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return products;
		}
	
	

}
