package com.inventory.service;

import com.inventory.models.Item;
import com.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll(Integer level, Integer userId) {
        return itemRepository.filterFindAll(level, userId);
    }

    public List<Item> searchItems(Integer userId, String search) {
        List<Item> result = new ArrayList<>();
        List<Item> items = itemRepository.searchItems(userId);
        String lowerSearch = search.toLowerCase();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(lowerSearch)
                    || item.getInformation().toLowerCase().contains(lowerSearch)
                    || item.getSerialNumber().toLowerCase().contains(lowerSearch)
                    || String.valueOf(item.getAge()).contains(lowerSearch)
                    || String.valueOf(item.getPrice()).contains(lowerSearch)) {
                result.add(item);
            }
        }
        return result;
    }

    public Optional<Item> getById(Integer id) {
        return itemRepository.findById(id);
    }

    public Item save(Item object) {
        return itemRepository.save(object);
    }

    public Optional<Item> update(Integer id, Item object) {
        Item dbObject = itemRepository.findById(id).orElseThrow();
        object.setId(dbObject.getId());
        return Optional.of(itemRepository.save(object));
    }

    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }
}
