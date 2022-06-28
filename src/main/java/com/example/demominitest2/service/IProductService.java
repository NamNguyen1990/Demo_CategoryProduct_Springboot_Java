package com.example.demominitest2.service;

import com.example.demominitest2.model.Product;

public interface IProductService extends IGeneralService<Product>{

    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByPriceAsc();

    Iterable<Product> findTop4();

    Iterable<Product> findAllByPriceBetween(int from, int to);

    Iterable<Product> findAllByCategory_Name(String name);

    Iterable<Product> findPriceTop2500();

    Iterable<Product> findPriceHoafrom1000to2000(int from, int to);

    Iterable<Product> findAllByCategory_Id(Long id);
}
