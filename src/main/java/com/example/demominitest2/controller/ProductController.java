package com.example.demominitest2.controller;


import com.example.demominitest2.model.Product;
import com.example.demominitest2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-price-asc")   // Sắp xếp giá từ thấp đến cao
    public ResponseEntity<Iterable<Product>> findAllByPriceAsc() {
        List<Product> products = (List<Product>) productService.findAllByPriceAsc();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-price-top")   // Sắp xếp giá từ thấp đến cao
    public ResponseEntity<Iterable<Product>> findTop4() {
        List<Product> products = (List<Product>) productService.findTop4();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-price-top2500")   // Sắp sản phẩm có giá từ 2500 trở lên
    public ResponseEntity<Iterable<Product>> findPriceTop2500() {
        List<Product> products = (List<Product>) productService.findPriceTop2500();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-price-from1000to2000")
    public ResponseEntity<Iterable<Product>> findPriceHoafrom1000to2000(@RequestParam int from, @RequestParam int to) {
        List<Product> products = (List<Product>) productService.findPriceHoafrom1000to2000(from, to);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductId(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Iterable<Product>> findCategoryId(@PathVariable Long id) {
        Iterable <Product> productOptional = productService.findAllByCategory_Id(id);
        return new ResponseEntity<>(productOptional, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Product> saveProduct (@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/searchname")  // Tìm kiếm tên gần đúng
    public ResponseEntity<List<Product>> searchName (@RequestParam String name) {
        List<Product> products = (List<Product>) productService.findAllByNameContaining(name);
        if (products == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/by-price-between")   // Sắp xếp giá từ thấp đến cao
    public ResponseEntity<Iterable<Product>> findAllByPriceBetween(@RequestParam int from, @RequestParam int to) {
        List<Product> products = (List<Product>) productService.findAllByPriceBetween(from, to);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/search-by-categoryname")  // Tìm kiếm tên gần đúng
    public ResponseEntity<List<Product>> findAllByCategory_Name (@RequestParam String name) {
        List<Product> products = (List<Product>) productService.findAllByCategory_Name(name);
        if (products == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
