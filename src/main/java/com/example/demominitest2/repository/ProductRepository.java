package com.example.demominitest2.repository;

import com.example.demominitest2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Tìm kiếm theo tên gần đúng
    Iterable<Product> findAllByNameContaining(String name);

    // Sắp xếp giá sản phẩm theo giá tăng dần
    @Query(value = "select * from Product order by price asc ", nativeQuery = true)
    Iterable<Product> findAllByPriceAsc();


    // Tìm 4 sản phẩm có giá cao nhất
    @Query(value = "select * from tryhard2.product order by price desc limit 4", nativeQuery = true)
    Iterable<Product> findTop4();

    // Tìm sản phẩm theo khoảng giá
    Iterable<Product> findAllByPriceBetween(int from, int to);

    // Tìm những sản phẩm theo loại nhập vào
    Iterable<Product> findAllByCategory_Name(String name);


    Iterable<Product> findAllByCategory_Id(Long id);

    // In ra các sản phẩm có giá từ 2500 trở lên.

    @Query(value = "select * from tryhard2.product where price >= 2500", nativeQuery = true)
    Iterable<Product> findPriceTop2500();



    // Tìm tất cả các loại Hoa có giá từ 1000-2000, từ from đến to
    @Query(value = "select *\n" +
            "from product p\n" +
            "join category c on c.id = p.category_id\n" +
            "where c.name like 'Hoa' and price between :from and :to ", nativeQuery = true)
    Iterable<Product> findPriceHoafrom1000to2000(@Param(value = "from") int from, @Param(value = "to") int to);






    // Các câu truy vấn
//    select * from tryhard2.product
//    order by price asc;
//
//    select * from tryhard2.product
//    order by price desc limit 3;
//
//    select * from tryhard2.product
//    where price >= 2500;
//
//    select *
//    from product
//    join category c on c.id = product.category_id
//    where c.name like 'Hoa' and price between 1000 and 2000;


//    select *
//    from product
//    join category c on c.id = product.category_id
//    where c.name like 'Hoa' and price between :from and 2000;
}
