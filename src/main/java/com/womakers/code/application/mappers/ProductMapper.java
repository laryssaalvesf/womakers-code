package com.womakers.code.application.mappers;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.application.entities.Product;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

  public static Product convertToProduct(final ProductRequestDTO productRequestDTO) {

    return Product
        .builder()
        .id(UUID.randomUUID().toString())
        .name(productRequestDTO.getName())
        .description(productRequestDTO.getDescription())
        .value(productRequestDTO.getValue())
        .build();
  }


  public static ProductResponseDTO convertToProductResponseDTO(final Product product) {

    return ProductResponseDTO
        .builder()
        .name(product.getName())
        .description(product.getDescription())
        .value(product.getValue())
        .build();
  }


}
