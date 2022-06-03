package com.womakers.code.adapters.inbounds;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.application.exceptions.ProductNotFoundException;
import com.womakers.code.application.services.ProductService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<ProductResponseDTO> saveProduct(
      @RequestBody final ProductRequestDTO productRequestDTO) {

    log.info("Saving the product");

    final ProductResponseDTO objPersisted = productService.save(productRequestDTO);

    log.info("Product persisted with success");

    return ResponseEntity.status(HttpStatus.CREATED).body(objPersisted);
  }



  @GetMapping("/products/{id}")
  public ResponseEntity<ProductResponseDTO> getProductById(
      @PathVariable final String id) {

    log.info("Getting product by id");

    final ProductResponseDTO objPersisted = productService.findById(id);


    log.info("Product found and returned with success");

    return ResponseEntity.status(HttpStatus.OK).body(objPersisted);
  }

}
