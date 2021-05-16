package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.TipsStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsStorageRepository extends JpaRepository<TipsStorage, Long> {

    List<TipsStorage> findAllByWorkCompanyName(String name);
}
