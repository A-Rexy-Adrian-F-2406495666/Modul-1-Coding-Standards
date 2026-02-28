package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateExistingProduct() {
        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("Old Name");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updated = new Product();
        updated.setProductId("p1");
        updated.setProductName("New Name");
        updated.setProductQuantity(20);

        Product result = productRepository.update(updated);
        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());

        Product fetched = productRepository.findById("p1");
        assertEquals("New Name", fetched.getProductName());
    }

    @Test
    void testUpdateNonExistingProduct() {
        Product updated = new Product();
        updated.setProductId("not-exist");
        updated.setProductName("Whatever");
        Product result = productRepository.update(updated);
        assertNull(result);
    }

    @Test
    void testFindByIdExisting() {
        Product product = new Product();
        product.setProductId("p2");
        product.setProductName("FindMe");
        product.setProductQuantity(5);
        productRepository.create(product);

        Product fetched = productRepository.findById("p2");
        assertNotNull(fetched);
        assertEquals("FindMe", fetched.getProductName());
    }

    @Test
    void testFindByIdNonExisting() {
        Product fetched = productRepository.findById("non-exist");
        assertNull(fetched);
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("p3");
        product.setProductName("ToDelete");
        productRepository.create(product);

        productRepository.deleteById("p3");
        assertNull(productRepository.findById("p3"));
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDeleteNonExistingProduct() {
        productRepository.deleteById("non-exist");
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testCreateProductWithoutId() {
        Product product = new Product();
        product.setProductName("No ID Product");
        product.setProductQuantity(10);

        Product saved = productRepository.create(product);
        assertNotNull(saved.getProductId());
        assertEquals("No ID Product", saved.getProductName());
    }

    @Test
    void testFindByIdWithNonMatchingId() {
        Product product = new Product();
        product.setProductId("pX");
        product.setProductName("Some Product");
        productRepository.create(product);

        Product result = productRepository.findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testDeleteByIdNonMatchingId() {
        Product product = new Product();
        product.setProductId("pY");
        product.setProductName("Another Product");
        productRepository.create(product);

        productRepository.deleteById("non-existent-id");
        
        Product fetched = productRepository.findById("pY");
        assertNotNull(fetched);
        assertEquals("Another Product", fetched.getProductName());
    }
}