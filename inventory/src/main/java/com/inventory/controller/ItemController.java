package com.inventory.controller;


import com.inventory.models.Item;
import com.inventory.security.Roles;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Secured({Roles.USER, Roles.ADMIN})
    @GetMapping
    public List<Item> getAll(@RequestParam(required = false) Integer level,
                             @RequestParam(required = true) Integer userId) {
        return itemService.getAll(level, userId);
    }

    @Secured({Roles.USER, Roles.ADMIN})
    @GetMapping("search")
    public List<Item> getSearchedItems(@RequestParam(required = true) Integer userId,
                                       @RequestParam(required = false) String search) {
        return itemService.searchItems(userId, search);
    }

    @GetMapping("{id}")
    public Optional<Item> getById(@PathVariable Integer id) {
        return itemService.getById(id);
    }

    @PostMapping
    public Item postObject(@RequestBody Item object) {
        return itemService.save(object);
    }

    @PutMapping("{id}")
    public Optional<Item> updateObject(@PathVariable Integer id,
                                       @RequestBody Item object) {
        return itemService.update(id, object);
    }

    @DeleteMapping("{id}")
    public void deleteObject(@PathVariable Integer id) {
        itemService.delete(id);
    }
}
