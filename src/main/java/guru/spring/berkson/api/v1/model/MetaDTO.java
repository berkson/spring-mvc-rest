package guru.spring.berkson.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:43
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaDTO {
    private Integer count;
    private Integer limite;
    private Integer page;
    private String previousUrl;
    private String nextUrl;
}
