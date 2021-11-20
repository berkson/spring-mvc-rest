package guru.spring.berkson.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private MetaDTO meta;
    private String firstname;
    private String lastname;
    private String customerUrl;
}
