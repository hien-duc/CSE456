package vn.edu.eiu.lab5.service;

import vn.edu.eiu.lab5.entity.Product;
import vn.edu.eiu.lab5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> createHardcodedProducts() {
        List<Product> products = Arrays.asList(
            new Product("Laptop", new BigDecimal("999.99")),
            new Product("Mouse", new BigDecimal("29.99")),
            new Product("Keyboard", new BigDecimal("79.99"))
        );

        for (Product product : products) {
            productRepository.save(product);
        }

        return products;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
