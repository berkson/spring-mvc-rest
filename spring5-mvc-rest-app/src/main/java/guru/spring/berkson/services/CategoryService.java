package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 23:09
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
