package guru.spring.berkson.api.v1.mapper;

import guru.spring.berkson.api.v1.model.CategoryDTO;
import guru.spring.berkson.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 21:52
 */
class CategoryMapperTest {

    @Test
    void categoryToCategoryDto() {
        //given
        Category category = new Category(1L, "Fruits");

        //when
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.categoryToCategoryDto(category);

        //then
        assertNotNull(categoryDTO);
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Fruits", categoryDTO.getName());
    }

    @Test
    void testCategoryNull() {
        Category category = null;

        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.categoryToCategoryDto(category);

        assertNull(categoryDTO);
    }
}