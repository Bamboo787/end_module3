package com.codegym.product_manager.service;

import com.codegym.product_manager.dto.ProductDTO;
import com.codegym.product_manager.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product>{
    List<ProductDTO> findAllProductDTO();

    List<ProductDTO> searchProduct(String keyword);
}


