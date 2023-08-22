package ch.letterix.coverletterservice.domain.response.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Message {
    private String role;
    private String content;

public Message() {
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
