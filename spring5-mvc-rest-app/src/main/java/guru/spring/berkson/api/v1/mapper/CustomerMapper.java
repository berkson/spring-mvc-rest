package guru.spring.berkson.api.v1.mapper;

import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.domain.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:48
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
