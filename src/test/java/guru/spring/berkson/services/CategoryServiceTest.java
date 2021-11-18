package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.mapper.CategoryMapper;
import guru.spring.berkson.api.v1.model.CategoryDTO;
import guru.spring.berkson.domain.Category;
import guru.spring.berkson.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 23:19
 */
class CategoryServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Diego";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertNotNull(categoryDTOS);
        assertEquals(2, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
    }
}