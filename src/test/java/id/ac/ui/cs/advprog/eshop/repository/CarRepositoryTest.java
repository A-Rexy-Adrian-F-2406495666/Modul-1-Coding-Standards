package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();

        car = new Car();
        car.setCarId("car-1");
        car.setCarName("Toyota");
        car.setCarColor("Black");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateCar() {
        Car created = carRepository.create(car);

        assertEquals("car-1", created.getCarId());
        assertEquals("Toyota", created.getCarName());
        assertEquals("Black", created.getCarColor());
        assertEquals(5, created.getCarQuantity());
    }

    @Test
    void testCreateCarWithoutId_ShouldGenerateId() {
        Car newCar = new Car();
        newCar.setCarName("Honda");
        newCar.setCarColor("White");
        newCar.setCarQuantity(3);

        Car created = carRepository.create(newCar);

        assertNotNull(created.getCarId());
    }

    @Test
    void testFindAll() {
        carRepository.create(car);

        Iterator<Car> iterator = carRepository.findAll();

        assertTrue(iterator.hasNext());
        Car found = iterator.next();

        assertEquals("car-1", found.getCarId());
    }

    @Test
    void testFindById_WhenCarExists() {
        carRepository.create(car);

        Car found = carRepository.findById("car-1");

        assertNotNull(found);
        assertEquals("Toyota", found.getCarName());
    }

    @Test
    void testFindById_WhenCarNotExists() {
        Car found = carRepository.findById("not-exist");

        assertNull(found);
    }

    @Test
    void testUpdate_WhenCarExists() {
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Mazda");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(10);

        Car result = carRepository.update("car-1", updatedCar);

        assertNotNull(result);
        assertEquals("Mazda", result.getCarName());
        assertEquals("Red", result.getCarColor());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testUpdate_WhenCarNotExists() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Mazda");

        Car result = carRepository.update("not-exist", updatedCar);

        assertNull(result);
    }

    @Test
    void testDeleteCar() {
        carRepository.create(car);

        carRepository.delete("car-1");

        Car found = carRepository.findById("car-1");
        assertNull(found);
    }
}