package guru.spring.berkson.bootstrap;

import guru.spring.berkson.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 19:37
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class BootstrapIT {

    @Autowired
    CategoryRepository categoryRepository;


    Bootstrap bootstrap;

    @BeforeEach
    void setUp() throws Exception {
        bootstrap = new Bootstrap(categoryRepository);
        bootstrap.run();
    }

    @Test
    void bootTest() throws Exception {
        assertEquals(6, categoryRepository.count());
    }
}