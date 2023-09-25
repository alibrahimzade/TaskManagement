package com.example.taskmanagement.model.mapper;

import com.example.taskmanagement.dao.entity.Item;
import com.example.taskmanagement.dao.entity.Member;
import com.example.taskmanagement.model.dto.ItemDto;
import com.example.taskmanagement.model.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto mapEntityToDto(Member member);
    Member mapDtoToEntity(MemberDto memberDto);
}
