package com.openclassrooms.mddapi.models;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing an user.
 *
 * @author Tony
 * @version 1.0
 */
// annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Data
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "users") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Schema(description = "User identifier", example = "1")
    @Id // clé primaire de la table
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté

    private Integer id;

    @Schema(description = "Username", example = "Robert")
    private String username;

    @Schema(description = "User email", example = "Robert@mail.com")
    private String email;

    @Schema(description = "User password", example = "1243pass")
    private String password;

    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Schema(description = "date this user was updated", example = "2023-03-18T00:23:42")
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Schema(description = "List of subscribed topics ", example = "List<Topic>")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subscribe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    @JsonIgnore
    private List<Topic> topics;

}
