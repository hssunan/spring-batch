package com.fastcampus.pass.repository.pass;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// 일치하지 않는 필드 무시
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassModelMapper {

    PassModelMapper INSTANCE = Mappers.getMapper(PassModelMapper.class);

    // 필드명이 같지 않거나 custom하게 매핑해주기 위해서는 @Mapping 추가
    @Mapping(target = "status", qualifiedByName = "defaultStatus")
    @Mapping(target = "remainingCount", source = "bulkPassEntity.count")
    PassEntity toPassEntity(BulkPassEntity bulkPassEntity, String userId);

    // BulkPassStatus와 관계 없이 PassStatus값을 설정
    @Named("defaultStatus")
    default PassStatus status(BulkPassStatus status) {
        return PassStatus.READY;
    }

}
