package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.response.TopicResponse;
import com.openclassrooms.mddapi.models.Topic;

@Component
@Mapper(componentModel = "spring")
public interface TopicMapper extends EntityMapper<TopicResponse, Topic> {

}
