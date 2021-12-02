package guru.spring.berkson.bootstrap;

import guru.spring.berkson.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Berkson Ximenes
 * Date: 24/11/2021
 * Time: 22:10
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
class CustomerBootStrapIT {

    @Autowired
    CustomerRepository customerRepository;

    CustomerBootStrap customerBootStrap;

    @BeforeEach
    void setUp() throws Exception {
        customerBootStrap = new CustomerBootStrap(customerRepository);
        customerBootStrap.run();
    }

    @Test
    void testSetCustomers() {

        assertEquals(3, customerRepository.count());
    }
}