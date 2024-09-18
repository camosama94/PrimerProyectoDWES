package com.iesjuanbosco.ejemploweb.controller;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Array;
import java.util.*;

@Controller //Anotación que le indica a Spring que esta clase es un controlador
public class ProductoController {

    //Para acceder al repositorio creamos una propiedad y la asignamos en el constructor
    private ProductoRepository productoRepository;

    public ProductoController(ProductoRepository repository){
        this.productoRepository = repository;
    }

    /* Con GetMapping indicamos que el metodo se va a ejecutar cuando se acceda a esta url
    http://localhost/productos*/

    @GetMapping("/productos")
    public String findAll(){
        List<Producto> productos = this.productoRepository.findAll();
        return "product-list";
    }

    @GetMapping("/productos/add")
    public String add(){
        List<Producto> productos = new ArrayList<Producto>();
        Producto p1 = new Producto(null,"Producto 1",20,30.0);
        Producto p2 = new Producto(null,"Producto 2",35,22.25);
        Producto p3 = new Producto(null,"Producto 3",50,17.50);
        Producto p4 = new Producto(null,"Producto 4",100,5.0);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        this.productoRepository.saveAll(productos);

        //redirige al controlador /productos
        return "redirect:/productos";
    }

}
