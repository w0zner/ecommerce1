package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.ProductRepository;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProductService {

    private final ProductRepository repo;
    private final UploadFile uploadFile;

    public ProductService(ProductRepository repo, UploadFile uploadFile) {
        this.repo = repo;
        this.uploadFile = uploadFile;
    }

    public Iterable<Product> getProducts() {
        return repo.getProducts();
    }

    public Iterable<Product> getProductByUser(User user) {
        return repo.getProductByUser(user);
    }

    public Product getProductById(Integer id) {
        return repo.getProductById(id);
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if(product.getId()==null) {
            User user = new User();
            user.setId(1);
            product.setDateCreated(LocalDateTime.now());
            product.setDateUpdated(LocalDateTime.now());
            product.setImage(uploadFile.upload(multipartFile));
            product.setUser(user);
            return repo.save(product);
        } else {
            Product productoExistente = repo.getProductById(product.getId());

            if(multipartFile.isEmpty()) {
                product.setImage(productoExistente.getImage());
            } else {
                product.setImage(uploadFile.upload(multipartFile));
            }

            product.setCode(productoExistente.getCode());
            product.setDateCreated(productoExistente.getDateCreated());
            product.setDateUpdated(LocalDateTime.now());
            product.setUser(productoExistente.getUser());

            return repo.save(product);
        }
    }

    public void delete(Integer id) {
        repo.delete(id);
    }
}
