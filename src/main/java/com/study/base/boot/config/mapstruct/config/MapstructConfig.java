package com.study.base.boot.config.mapstruct.config;

import com.study.base.boot.config.mapstruct.base.BaseEntity;
import org.hibernate.Hibernate;
import org.mapstruct.MapperConfig;

import java.util.List;

@MapperConfig
public interface MapstructConfig {
    default boolean isLoaded(List<? extends BaseEntity> entities){
        return Hibernate.isInitialized(entities);
    }

    default boolean isLoaded(BaseEntity entity) {
        return Hibernate.isInitialized(entity);
    }

    /*
     *  MapperConfig : 컨피그타이으로 인식한다.
     * entities //리스ㅡ트인경우
     * entity//단건인경우
     * Hibernate.isInitialized( //변수가 초기화된 상태인지 아닌지확인 조회가 된건지 확인
     */
}
