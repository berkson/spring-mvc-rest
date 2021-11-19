package guru.spring.berkson.api.v1.mapper;

import guru.spring.berkson.api.v1.model.MetaDTO;
import guru.spring.berkson.domain.Meta;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:45
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetaMapper {
    MetaMapper INSTANCE = Mappers.getMapper(MetaMapper.class);

    MetaDTO metaToMetaDTO(Meta meta);
}
