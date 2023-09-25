package com.example.taskmanagement.service;

import com.example.taskmanagement.dao.entity.Item;
import com.example.taskmanagement.dao.repository.ItemRepository;
import com.example.taskmanagement.enums.Status;
import com.example.taskmanagement.model.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.example.taskmanagement.model.mapper.ItemMapper.INSTANCE;
import static org.hibernate.engine.spi.Status.DELETED;
import static org.hibernate.engine.spi.Status.SAVING;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public ResponseEntity<?> updateItem(ItemDto itemDto, Long itemId){
        Item item = itemRepository.findById(itemId).orElseGet(() -> null);
        if (Objects.nonNull(item)){
            Item updated = INSTANCE.mapDtoToEntity(itemDto);
            updated.setName(updated.getName());
            updated.setDescription(updated.getDescription());
            updated.setStatus(updated.getStatus());

            itemRepository.save(updated);
            return ResponseEntity.status(OK).body("Item is updated");
        }
        return ResponseEntity.status(NOT_FOUND).body("Item doest exist");
    }

    public ResponseEntity<?> createItem(ItemDto itemDto){
        Item itemName = itemRepository.getItemByName(itemDto.getName()).orElseGet(() -> null);
        if (Objects.isNull(itemName)){
            Item item = INSTANCE.mapDtoToEntity(itemDto);
            itemDto.setStatus(Status.TO_DO);
            itemRepository.save(item);
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(CONFLICT).body("Item is already exist");
    }

    public ResponseEntity<?> changeStatus(Long itemId, Status newStatus){
        Item item = itemRepository.findById(itemId).get();
        if (item == null){
            return ResponseEntity.status(NOT_FOUND).body("Item not found");
        }
        item.setStatus(newStatus);
        itemRepository.save(item);
        return ResponseEntity.status(OK).body("Status updated");
    }

    public ResponseEntity<?> getItemById(Long id){
        Item item = itemRepository.findById(id).orElseGet(()-> null);
        if (Objects.nonNull(item)){
            return ResponseEntity.ok(INSTANCE.mapEntityToDto(item));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item doesn't exist");
    }
    public ResponseEntity<?> getItemByName(String name){
        Item item = itemRepository.getItemByName(name).orElseGet(()-> null);
        if (Objects.nonNull(item)){
            return ResponseEntity.ok(INSTANCE.mapEntityToDto(item));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item doesn't exist");
    }

    public ResponseEntity<?> deleteItem(Long id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.status(OK).body(DELETED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item doesn't exist");
    }
}
