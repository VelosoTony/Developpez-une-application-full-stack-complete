package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.response.PostResponse;
import com.openclassrooms.mddapi.models.Post;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostResponse, Post> {

}
