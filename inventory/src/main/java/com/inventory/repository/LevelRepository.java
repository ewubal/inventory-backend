package com.inventory.repository;

import com.inventory.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {

    @Query(value = "SELECT n FROM Level n WHERE n.levelId =?1 AND n.id <>1 AND n.userId =?2")
    List<Level> filterFindAll(Integer levelId, Integer userId);
}
