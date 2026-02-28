package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;
    private ProductService service;
    private ProductController controller;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setup() {
        service = mock(ProductService.class);

        controller = new ProductController(service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
    }

    @Test
    void createProductPage_ShouldReturnCreateProductView() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createProductPost_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productId", "1")
                        .param("productName", "Product 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void productListPage_ShouldReturnProductListViewWithProducts() throws Exception {
        List<Product> products = Arrays.asList(product1, product2);
        when(service.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));

        verify(service, times(1)).findAll();
    }

    @Test
    void editProductPage_ShouldReturnEditProductViewWithProduct() throws Exception {
        when(service.findById("1")).thenReturn(product1);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));

        verify(service, times(1)).findById("1");
    }

    @Test
    void editProductPost_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("productId", "1")
                        .param("productName", "Updated Product"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).update(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldCallServiceAndRedirect() throws Exception {
        doNothing().when(service).deleteById("1");

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).deleteById("1");
    }
}