package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
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

class CarControllerTest {

    private MockMvc mockMvc;
    private CarService service;
    private CarController controller;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setup() {
        service = mock(CarService.class);
        controller = new CarController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        car1 = new Car();
        car1.setCarId("1");
        car1.setCarName("Toyota");
        car1.setCarColor("Black");
        car1.setCarQuantity(5);

        car2 = new Car();
        car2.setCarId("2");
        car2.setCarName("Honda");
        car2.setCarColor("White");
        car2.setCarQuantity(3);
    }

    @Test
    void createCarPage_ShouldReturnCreateCarView() throws Exception {
        mockMvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateCar"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    void createCarPost_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/car/createCar")
                        .param("carId", "1")
                        .param("carName", "Toyota")
                        .param("carColor", "Black")
                        .param("carQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(service, times(1)).create(any(Car.class));
    }

    @Test
    void carListPage_ShouldReturnCarListViewWithCars() throws Exception {
        List<Car> cars = Arrays.asList(car1, car2);
        when(service.findAll()).thenReturn(cars);

        mockMvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("CarList"))
                .andExpect(model().attributeExists("cars"));

        verify(service, times(1)).findAll();
    }

    @Test
    void editCarPage_ShouldReturnEditCarViewWithCar() throws Exception {
        when(service.findById("1")).thenReturn(car1);

        mockMvc.perform(get("/car/editCar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditCar"))
                .andExpect(model().attributeExists("car"));

        verify(service, times(1)).findById("1");
    }

    @Test
    void editCarPost_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/car/editCar")
                        .param("carId", "1")
                        .param("carName", "Updated Toyota")
                        .param("carColor", "Red")
                        .param("carQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(service, times(1)).update(eq("1"), any(Car.class));
    }

    @Test
    void deleteCar_ShouldCallServiceAndRedirect() throws Exception {
        doNothing().when(service).deleteCarById("1");

        mockMvc.perform(post("/car/deleteCar")
                        .param("carId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(service, times(1)).deleteCarById("1");
    }
}