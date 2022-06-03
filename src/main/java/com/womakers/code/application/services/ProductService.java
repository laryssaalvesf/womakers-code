package com.womakers.code.application.services;

import static com.womakers.code.application.mappers.ProductMapper.convertToProduct;
import static com.womakers.code.application.mappers.ProductMapper.convertToProductResponseDTO;

import com.womakers.code.adapters.dtos.ProductRequestDTO;
import com.womakers.code.adapters.dtos.ProductResponseDTO;
import com.womakers.code.adapters.outbounds.ProductRepository;
import com.womakers.code.application.entities.Product;
import com.womakers.code.application.exceptions.ProductException;
import com.womakers.code.application.exceptions.ProductNotFoundException;
import com.womakers.code.application.mappers.ProductMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponseDTO save(final ProductRequestDTO productRequestDTO) {
    try {
      final Product product = convertToProduct(productRequestDTO);
      final Product productPeristed = productRepository.save(product);

      return convertToProductResponseDTO(productPeristed);
    } catch (Exception exception) {
      throw new ProductException("There was an error saving the product in the database");
    }
  }

  public ProductResponseDTO findById(final String id) {
    try {
      final Optional<Product> productFound = productRepository.findById(id);

      if(!productFound.isPresent()) {
        throw new ProductNotFoundException();
      }

      return productFound.map(ProductMapper::convertToProductResponseDTO).orElse(null);
    } catch (Exception exception) {
      throw new ProductNotFoundException("Product not found in database");
    }
  }
}
