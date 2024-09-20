package com.iesjuanbosco.ejemploweb.controller;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/productos2")
    @ResponseBody
    public String index(){
        List<Producto> productos = this.productoRepository.findAll();
        StringBuilder HTML = new StringBuilder("<html><body>");
        productos.forEach(producto -> {
            HTML.append("<p>" + producto.getTitulo() + "</p>");
        });
        HTML.append("</body></html>");
        return HTML.toString();
    }

    @GetMapping("/productos")
    public String findAll(Model model){
        List<Producto> productos = this.productoRepository.findAll();
        model.addAttribute("productos",productos);
        return "product-list";
    }

    @GetMapping("/productos/add")
    public String add(){
        List<Producto> productos = new ArrayList<Producto>();
        Producto p1 = new Producto(null, "Producto 1", 20, 30.0);
        Producto p2 = new Producto(null, "Producto 2", 35, 22.25);
        Producto p3 = new Producto(null, "Producto 3", 50, 17.50);
        Producto p4 = new Producto(null, "Producto 4", 100, 5.0);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        //this.productoRepository.saveAll(productos);
        productoRepository.saveAll(productos);

        //redirige al controlador /productos
        return "redirect:/productos";
    }

    @GetMapping("/productos/del/{id}")
    public String delete(@PathVariable long id){
        //Borrar el producto usando el repositorio
        productoRepository.deleteById(id);

        //Redirigir al listado de productos
        return "redirect:/productos";
    }

    @GetMapping("/productos/view/{id}")
    public String view(@PathVariable long id, Model model){
        //Borrar el producto usando el repositorio
        Optional producto = productoRepository.findById(id);

        if(producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            //Redirigir al listado de productos
            return "product-view.html";
        }else{
            return "redirect:/productos";
        }
    }

    @GetMapping("/productos/new")
    public String newProductView(Model model){
        model.addAttribute("producto", new Producto());
        return "product_new.html";
    }

    @PostMapping("/productos/new")
    public String newProductInsert(Producto producto){
        //Insertamos los datos en la BBDD
        //Integer cant = Integer.parseInt(cantidad);
        //Double price = Double.parseDouble(precio);
        //Producto producto = new Producto(null,titulo,cantidad,precio);
        productoRepository.save(producto);
        //redirigimos a la lista de productos /productos
        return "redirect:/productos";
    }


    @GetMapping("/productos/edit")
    public String editProductView(Model model, Long id){
        Optional producto = productoRepository.findById(id);
        model.addAttribute("producto", producto);
        return "product_edit.html";
    }

    @PostMapping("/productos/edit")
    public String editProduct(Producto producto){
        //Insertamos los datos en la BBDD
        //Integer cant = Integer.parseInt(cantidad);
        //Double price = Double.parseDouble(precio);
        //Producto producto = new Producto(null,titulo,cantidad,precio);
        //productoRepository.;
        //redirigimos a la lista de productos /productos
        return "redirect:/productos";
    }
}
