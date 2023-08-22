package ch.letterix.coverletterservice.domain.response.entity;


import ch.letterix.coverletterservice.core.generic.AbstractEntity;
import ch.letterix.coverletterservice.domain.cover_letter.CoverLetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chat_completion")
@JsonIgnoreProperties(value = { "id", "content", "choices" })
public class ChatCompletion extends AbstractEntity {
    private String object;
    private long created;
    private String model;
    @Column(name = "content")
    private String content;
    @Embedded
    private Usage usage;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cover_letter_id")
    private CoverLetter coverLetter;

    public ChatCompletion() {}


    public ChatCompletion(UUID id, String object, long created, String model, Usage usage) {
        super(id);
        this.object = object;
        this.created = created;
        this.model = model;
        this.usage = usage;
    }
}
