package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a comment on a post.
 *
 * @author Tony
 * @version $Id: $Id
 */
@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "comments") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Schema(description = "Comment identifier", example = "1")
    @Id // clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    @Column(name = "comment_id")
    private Integer id;

    @Schema(description = "Post identifier", example = "1")
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Schema(description = "User identifier", example = "1")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Schema(description = "Content", example = "Not agree with this article!.")
    private String content;

    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
