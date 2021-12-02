package guru.spring.berkson.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 15:46
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    @ApiModelProperty(value = "descrição da categoria", required = true)
    private String name;
}
