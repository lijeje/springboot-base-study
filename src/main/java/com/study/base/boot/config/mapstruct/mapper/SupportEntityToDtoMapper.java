package com.study.base.boot.config.mapstruct.mapper;

import com.study.base.boot.config.mapstruct.base.BaseDto;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import com.study.base.boot.config.mapstruct.config.MapstructConfig;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SupportEntityToDtoMapper<E extends BaseEntity, D extends BaseDto>  extends MapstructConfig {
    E toEntity(D d); //d소스 E타겟

    D toDto(E e);
}
