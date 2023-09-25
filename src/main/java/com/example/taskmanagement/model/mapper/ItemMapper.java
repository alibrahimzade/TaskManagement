package com.example.taskmanagement.model.mapper;

import com.example.taskmanagement.dao.entity.Item;
import com.example.taskmanagement.model.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE= Mappers.getMapper(ItemMapper.class);


    ItemDto mapEntityToDto(Item item);
    Item mapDtoToEntity(ItemDto itemDto);
}
