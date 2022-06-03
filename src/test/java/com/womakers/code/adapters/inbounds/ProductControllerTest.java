package com.womakers.code.adapters.inbounds;

import static com.womakers.code.fixtures.ProductFixture.gimmeAProductRequestDTO;
import static com.womakers.code.fixtures.ProductFixture.gimmeAProductResponseDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.application.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {


  @InjectMocks
  ProductController productController;

  @Mock
  ProductService productService;


  @Test
  void shouldSaveProduct() {

    //Organizar ---------------------------------------------------------------------------
    final ProductRequestDTO productRequestDTO = gimmeAProductRequestDTO();
    final ProductResponseDTO productResponseDTO = gimmeAProductResponseDTO();
    when(productService.save(any())).thenReturn(productResponseDTO);


    //Agir --------------------------------------------------------------------------------
    final ResponseEntity<ProductResponseDTO> response = productController.saveProduct(
        productRequestDTO);
    final ProductResponseDTO productResponseDTO1 = response.getBody();


    //Afirmar ------------------------------------------------------------------------------
    verify(productService, times(1)).save(any());

    Assertions.assertEquals(productRequestDTO.getName(), productResponseDTO1.getName());
    Assertions.assertEquals(productRequestDTO.getDescription(), productResponseDTO1.getDescription());
    Assertions.assertEquals(productRequestDTO.getValue(), productResponseDTO1.getValue());
  }




  @Test
  void shouldGetProductById() {

    //Organizar ---------------------------------------------------------------------------
    final ProductResponseDTO productResponseDTO = gimmeAProductResponseDTO();
    when(productService.findById(anyString())).thenReturn(productResponseDTO);

    //Agir --------------------------------------------------------------------------------
    final ResponseEntity<ProductResponseDTO> response = productController.getProductById("1");
    final ProductResponseDTO result = response.getBody();


    //Afirmar ------------------------------------------------------------------------------
    verify(productService, times(1)).findById(anyString());
    Assertions.assertEquals(productResponseDTO.getName(), result.getName());
    Assertions.assertEquals(productResponseDTO.getDescription(), result.getDescription());
    Assertions.assertEquals(productResponseDTO.getValue(), result.getValue());
  }
}