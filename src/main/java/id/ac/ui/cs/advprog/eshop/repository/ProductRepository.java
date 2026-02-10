package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product update(Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            String currentProductId = productData.get(i).getProductId();
            String updatedProductId = updatedProduct.getProductId();
            if (currentProductId.equals(updatedProductId)) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            String currentProductId = product.getProductId();
            if (currentProductId.equals(productId)) {
                return product;
            }
        }
        return null;
    }
}
