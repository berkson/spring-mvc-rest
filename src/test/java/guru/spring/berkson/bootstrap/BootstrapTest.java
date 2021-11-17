package guru.spring.berkson.bootstrap;

import guru.spring.berkson.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 19:37
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BootstrapTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void bootTest() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository);
        bootstrap.run();

        assertEquals(5, categoryRepository.count());
    }
}