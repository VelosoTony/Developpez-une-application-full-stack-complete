package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "posts") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Schema(description = "Post identifier", example = "1")
    @Id // clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer post_id;

    @Schema(description = "Topic identifier", example = "1")
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Schema(description = "User identifier", example = "1")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Schema(description = "Title", example = "New version of Java")
    private String title;

    @Schema(description = "Content", example = "Lot of new features for this new version of Java.")
    private String content;

    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    private LocalDateTime created_date;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}