package com.openclassrooms.mddapi.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "topics") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Topic {

    @Schema(description = "Topic identifier", example = "1")
    @Id // clé primaire de la table
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer id;

    @Schema(description = "Name", example = "Java")
    private String name;

    @Schema(description = "Description", example = "Java a POO language.")
    private String description;

}