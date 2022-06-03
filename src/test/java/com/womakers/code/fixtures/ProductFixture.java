package com.womakers.code.fixtures;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.application.entities.Product;

public class ProductFixture {

  public static ProductResponseDTO gimmeAProductResponseDTO() {

    return ProductResponseDTO.builder()
        .name("Lipstick")
        .description("Color your lips")
        .value(15)
        .build();

  }


  public static ProductRequestDTO gimmeAProductRequestDTO() {

    return ProductRequestDTO.builder()
        .name("Lipstick")
        .description("Color your lips")
        .value(15)
        .build();

  }

  public static Product gimmeAProduct() {

    return Product.builder()
        .name("Lipstick")
        .description("Color your lips")
        .value(15)
        .build();

  }

}
