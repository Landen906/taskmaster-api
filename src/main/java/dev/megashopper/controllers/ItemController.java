package dev.megashopper.controllers;

import dev.megashopper.common.dtos.ItemResponsePayload;
import dev.megashopper.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    public List<ItemResponsePayload> getAllItems() {
        return itemService.fetchAllItems();
    }

    @DeleteMapping()
    public void deleteItem(String id) {
        itemService.deleteitemById(id);
    }
}
