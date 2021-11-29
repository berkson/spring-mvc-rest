package guru.spring.berkson.api.v1.controllers;

import guru.spring.berkson.api.v1.model.CategoryDTO;
import guru.spring.berkson.api.v1.model.CategoryListDTO;
import guru.spring.berkson.config.SwaggerConfig;
import guru.spring.berkson.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 20:26
 */
@RestController
@RequestMapping(value = "/api/v1/categories")
@Api(tags = {SwaggerConfig.CATEGORY_TAG}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Lista todas as categorias")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryListDTO> listCategories() {
        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna a categoria com a nome/descrição solicitada")
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getByNameCategories(@PathVariable String name) {
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }

}
