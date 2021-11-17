package guru.spring.berkson.bootstrap;

import guru.spring.berkson.domain.Category;
import guru.spring.berkson.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 19:06
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.saveCategories();
    }

    private void saveCategories(){
        List<Category> categoryList = Arrays.asList(new Category("Fruits"),
                new Category("Dried"), new Category("Fresh"), new Category("Exotic"),
                new Category("Nuts"));
        categoryRepository.saveAll(categoryList);
        System.out.println("Data Loaded: " + categoryRepository.count());
    }

}
