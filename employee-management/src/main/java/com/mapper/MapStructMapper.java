package com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;


import com.entity.User;

/*This class is used to convert dtos to entities and vice a versa*/
@Mapper
public interface MapStructMapper {
	
	//will implement it when we will have alot of dtos and entities
	MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);
 
}
