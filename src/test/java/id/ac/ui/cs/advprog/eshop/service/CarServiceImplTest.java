package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

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
    void create_ShouldCallRepositoryAndReturnCar() {
        when(carRepository.create(car1)).thenReturn(null);

        Car result = carService.create(car1);

        verify(carRepository, times(1)).create(car1);
        assertEquals(car1, result);
    }

    @Test
    void findAll_ShouldReturnListOfAllCars() {
        Iterator<Car> carIterator = Arrays.asList(car1, car2).iterator();
        when(carRepository.findAll()).thenReturn(carIterator);

        List<Car> allCars = carService.findAll();

        verify(carRepository, times(1)).findAll();
        assertEquals(2, allCars.size());
        assertTrue(allCars.contains(car1));
        assertTrue(allCars.contains(car2));
    }

    @Test
    void findById_ShouldReturnCarFromRepository() {
        when(carRepository.findById("1")).thenReturn(car1);

        Car result = carService.findById("1");

        verify(carRepository, times(1)).findById("1");
        assertEquals(car1, result);
    }

    @Test
    void update_ShouldCallRepositoryUpdate() {
        when(carRepository.update("1", car1)).thenReturn(car1);

        carService.update("1", car1);

        verify(carRepository, times(1)).update("1", car1);
    }

    @Test
    void deleteCarById_ShouldCallRepositoryDelete() {
        doNothing().when(carRepository).delete("1");

        carService.deleteCarById("1");

        verify(carRepository, times(1)).delete("1");
    }
}