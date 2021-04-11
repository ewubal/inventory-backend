package com.inventory.controller;

import com.inventory.models.Level;
import com.inventory.security.Roles;
import com.inventory.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("levels")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @Secured({Roles.USER, Roles.ADMIN})
    @GetMapping
    public List<Level> getAll(@RequestParam(required = false) Integer level,
                              @RequestParam(required = true) Integer userId) {
        return levelService.getAll(level, userId);
    }

    @PostMapping
    public Level postLevel(@RequestBody Level level) {
        return levelService.save(level);
    }

    @GetMapping("{id}")
    public Optional<Level> getById(@PathVariable Integer id) {
        return levelService.getById(id);
    }

    @PutMapping("{id}")
    public Optional<Level> updateLevel(@PathVariable Integer id,
                                       @RequestBody Level level) {
        return levelService.update(id, level);
    }

    @DeleteMapping("{id}")
    public void deleteLevel(@PathVariable Integer id) {
        levelService.delete(id);
    }
}
