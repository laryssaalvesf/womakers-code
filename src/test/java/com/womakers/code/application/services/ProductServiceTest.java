package com.womakers.code.application.services;

import static com.womakers.code.fixtures.ProductFixture.gimmeAProduct;
import static com.womakers.code.fixtures.ProductFixture.gimmeAProductRequestDTO;
import static com.womakers.code.fixtures.ProductFixture.gimmeAProductResponseDTO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.adapters.outbounds.ProductRepository;
import com.womakers.code.application.entities.Product;
import com.womakers.code.application.exceptions.ProductException;
import com.womakers.code.application.exceptions.ProductNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


  @InjectMocks
  ProductService productService;

  @Mock
  ProductRepository productRepository;


  @Test
  void shouldSave() {

    final ProductRequestDTO productRequestDTO = gimmeAProductRequestDTO();

    when(productRepository.save(any())).thenReturn(gimmeAProduct());

    final ProductResponseDTO responseDTO = productService.save(
        productRequestDTO);

    verify(productRepository, times(1)).save(any());

    Assertions.assertEquals(productRequestDTO.getName(), responseDTO.getName());
    Assertions.assertEquals(productRequestDTO.getDescription(), responseDTO.getDescription());
    Assertions.assertEquals(productRequestDTO.getValue(), responseDTO.getValue());
  }

  @Test
  void shouldFindById() {
    final Product product = gimmeAProduct();
    final ProductResponseDTO expectedResponseDTO = gimmeAProductResponseDTO();

    when(productRepository.findById(anyString())).thenReturn(Optional.ofNullable(product));

    final ProductResponseDTO responseDTO = productService.findById("1");

    verify(productRepository, times(1)).findById(anyString());

    Assertions.assertEquals(expectedResponseDTO.getName(), responseDTO.getName());
    Assertions.assertEquals(expectedResponseDTO.getDescription(), responseDTO.getDescription());
    Assertions.assertEquals(expectedResponseDTO.getValue(), responseDTO.getValue());

  }

  @Test
  void shouldNotSaveWithException() {

    final ProductRequestDTO productRequestDTO = gimmeAProductRequestDTO();

    when(productRepository.save(any())).thenThrow(NullPointerException.class);

    assertThrows(ProductException.class,
        () -> productService.save(
            productRequestDTO));

  }

  @Test
  void shouldNotFindByIdWithException() {

    when(productRepository.findById(anyString())).thenThrow(NullPointerException.class);

    assertThrows(ProductNotFoundException.class,
        () -> productService.findById("1"));

  }
}