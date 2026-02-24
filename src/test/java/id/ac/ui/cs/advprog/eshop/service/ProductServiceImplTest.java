package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
    }

    @Test
    void create_ShouldCallRepositoryAndReturnProduct() {
        when(productRepository.create(product1)).thenReturn(null);
        Product result = productService.create(product1);

        verify(productRepository, times(1)).create(product1);
        assertEquals(product1, result);
    }

    @Test
    void update_ShouldCallRepositoryAndReturnUpdatedProduct() {
        when(productRepository.update(product1)).thenReturn(null);
        Product result = productService.update(product1);

        verify(productRepository, times(1)).update(product1);
        assertEquals(product1, result);
    }

    @Test
    void findAll_ShouldReturnListOfAllProducts() {
        Iterator<Product> productIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(product1));
        assertTrue(allProducts.contains(product2));
    }

    @Test
    void findById_ShouldReturnProductFromRepository() {
        when(productRepository.findById("1")).thenReturn(product1);

        Product result = productService.findById("1");

        verify(productRepository, times(1)).findById("1");
        assertEquals(product1, result);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        doNothing().when(productRepository).deleteById("1");

        productService.deleteById("1");

        verify(productRepository, times(1)).deleteById("1");
    }
}