package com.womakers.code.application.mappers;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.application.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductMapperTest {


  @Test
  void shouldConvertToProduct() {
    final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().name("Gloss")
        .description("some description").value(20).build();
    final Product product = ProductMapper.convertToProduct(productRequestDTO);

    Assertions.assertEquals(productRequestDTO.getName(), product.getName());
    Assertions.assertEquals(productRequestDTO.getDescription(), product.getDescription());
    Assertions.assertEquals(productRequestDTO.getValue(), product.getValue());
  }

  @Test
  void shouldConvertToProductResponseDTO() {
    final Product product = Product.builder().name("Gloss")
        .description("some description").value(20).build();
    final ProductResponseDTO productResponseDTO = ProductMapper.convertToProductResponseDTO(
        product);

    Assertions.assertEquals(product.getName(), productResponseDTO.getName());
    Assertions.assertEquals(product.getDescription(), productResponseDTO.getDescription());
    Assertions.assertEquals(product.getValue(), productResponseDTO.getValue());
  }
}