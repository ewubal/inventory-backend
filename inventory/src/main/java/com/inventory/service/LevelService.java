package com.inventory.service;

import com.inventory.models.Level;
import com.inventory.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public List<Level> getAll(Integer level, Integer userId) {
        return levelRepository.filterFindAll(level, userId);
    }

    public Optional<Level> getById(Integer id) {
        return levelRepository.findById(id);
    }

    public Level save(Level level) {
        return levelRepository.save(level);
    }

    public Optional<Level> update(Integer id, Level level) {
        Level dbLevel = levelRepository.findById(id).orElseThrow();
        level.setId(dbLevel.getId());
        return Optional.of(levelRepository.save(level));
    }

    public void delete(Integer id) {
        levelRepository.deleteById(id);
    }
}
