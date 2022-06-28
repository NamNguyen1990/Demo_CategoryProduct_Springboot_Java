package com.example.demominitest2.service.impl;

import com.example.demominitest2.model.Product;
import com.example.demominitest2.repository.ProductRepository;
import com.example.demominitest2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }


    @Override
    public Iterable<Product> findAllByPriceAsc() {
        return productRepository.findAllByPriceAsc();
    }

    @Override
    public Iterable<Product> findTop4() {
        return productRepository.findTop4();
    }

    @Override
    public Iterable<Product> findAllByPriceBetween(int from, int to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public Iterable<Product> findAllByCategory_Name(String name) {
        return productRepository.findAllByCategory_Name(name);
    }

    @Override
    public Iterable<Product> findPriceTop2500() {
        return productRepository.findPriceTop2500();
    }

    @Override
    public Iterable<Product> findPriceHoafrom1000to2000(int from, int to) {
        return productRepository.findPriceHoafrom1000to2000(from, to);
    }

    @Override
    public Iterable<Product> findAllByCategory_Id(Long id) {
        return productRepository.findAllByCategory_Id(id);
    }

//    @Override
//    public Iterable<Product> findPriceHoafrom1000to2000() {
//        return productRepository.findPriceHoafrom1000to2000();
//    }

}
