package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Postimg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostimgRepository extends JpaRepository<Postimg, Long> {
}
