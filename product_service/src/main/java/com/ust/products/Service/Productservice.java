package com.ust.products.Service;

import com.ust.products.Model.Product;
import com.ust.products.Repository.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Productservice {

    @Autowired
    private Productrepo productrepo;

    public List<Product> getAllProducts() {
        return productrepo.findAll();
    }

    public Product getProductById(Integer id) {
        return productrepo.findById(id)
                .orElseThrow();
    }

    public Product createProduct(Product product) {
        return productrepo.save(product);
    }

    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImageUrl(product.getImageUrl());
        return productrepo.save(existingProduct);
    }

    public void deleteProduct(Integer id) {
        productrepo.deleteById(id);
    }

    public List<Product> createMultipleProducts(List<Product> products) {
        List<Product> createdProducts = productrepo.saveAll(products);
        return createdProducts;
    }



}
