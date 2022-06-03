package com.womakers.code.adapters.outbounds;

import com.womakers.code.application.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
