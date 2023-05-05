package com.openclassrooms.mddapi.payload.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.openclassrooms.mddapi.models.Topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicListResponse {
    @Schema(description = "Topic list")
    private List<Topic> topics;

}
