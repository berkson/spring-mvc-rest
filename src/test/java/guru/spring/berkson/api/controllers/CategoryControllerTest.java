package guru.spring.berkson.api.controllers;

import guru.spring.berkson.api.v1.model.CategoryDTO;
import guru.spring.berkson.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 20:30
 */
class CategoryControllerTest {
    public static String NAME1 = "John";
    public static String NAME2 = "José";
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .build();
    }

    @Test
    void testListCategories() throws Exception {
        //given
        List<CategoryDTO> categoryDTOList = Arrays.asList(new CategoryDTO(1L, NAME1),
                new CategoryDTO(2L, NAME2));
        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);
        //when
        mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void testGetByNameCategories() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO(1L, NAME2);

        when(categoryService.getCategoryByName(NAME2)).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/categories/" + NAME2)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME2)));
    }
}