package com.fastcampus.pass.repository.packaze;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

    // query method 방식.. 네이밍 규칙 존재
    List<PackageEntity> findByCreatedAtAfter(LocalDateTime dateTime, Pageable packageSeq);

    @Modifying // insert, update, delete에 포함시켜야 됨.. 포함 x -> 에러 발생
    @Transactional // update, delete에 포함
    @Query(value = "UPDATE PackageEntity p" +
            "       SET p.count = :count,"  +
            "           p.period = :period" +
            "       WHERE p.packageSeq = :packageSeq")
    int updateCountAndPeriod(Integer packageSeq, Integer count, Integer period);
}
