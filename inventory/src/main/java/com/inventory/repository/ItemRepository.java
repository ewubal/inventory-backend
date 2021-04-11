package com.inventory.repository;

import com.inventory.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT n FROM Item n WHERE n.levelId=?1 AND n.userId=?2")
    List<Item> filterFindAll(Integer level, Integer userId);

    @Query(value = "SELECT n FROM Item n WHERE n.userId=?1")
    List<Item> searchItems(Integer userId);
}
