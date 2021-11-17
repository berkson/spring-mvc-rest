package guru.spring.berkson.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 19:17
 */
class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Fruits");
    }

    // Test Alphabetic Order. Natural Order
    @Test
    void compareTwoCategories() {
        Category cat = new Category(Long.valueOf(1L), "Fruits");
        Category cat1 = new Category(Long.valueOf(1L), "Herbs");

        assertEquals(0, cat.compareTo(category));
        assertEquals(-1, cat.compareTo(cat1));
    }
}