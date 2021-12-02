package guru.spring.berkson.api.v1.mapper;

import guru.spring.berkson.api.v1.model.CategoryDTO;
import guru.spring.berkson.domain.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 21:09
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    CategoryDTO categoryToCategoryDto(Category category);
}
