package com.openclassrooms.mddapi.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "users") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Schema(description = "User identifier", example = "1")
    @Id // clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer user_id;

    @Schema(description = "Username", example = "Robert")
    private String username;

    @Schema(description = "User email", example = "Robert@mail.com")
    private String email;

    @Schema(description = "User password", example = "1243pass")
    private String password;

    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    private LocalDateTime created_date;

    @Schema(description = "date this user was updated", example = "2023-03-18T00:23:42")
    @LastModifiedDate
    private LocalDateTime updated_date;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}