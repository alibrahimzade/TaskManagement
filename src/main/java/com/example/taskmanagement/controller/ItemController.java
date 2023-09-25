package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.dto.ItemDto;
import com.example.taskmanagement.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/byId/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable(name = "itemId") Long id){
        return itemService.getItemById(id);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<?> getItemByName(@PathVariable(name = "name") String name){
        return itemService.getItemByName(name);
    }

    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto){
        return itemService.createItem(itemDto);
    }

    @PutMapping("/updateItem/{itemId}")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto,
                                        @PathVariable(name = "itemId") Long itemId){
        return itemService.updateItem(itemDto,itemId);
    }

    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "itemId") Long itemId){
        return itemService.deleteItem(itemId);
    }
}
