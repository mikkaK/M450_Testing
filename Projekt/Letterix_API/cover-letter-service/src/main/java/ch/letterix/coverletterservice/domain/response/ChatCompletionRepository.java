package ch.letterix.coverletterservice.domain.response;

import ch.letterix.coverletterservice.core.generic.AbstractRepository;
import ch.letterix.coverletterservice.domain.response.entity.ChatCompletion;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatCompletionRepository extends AbstractRepository<ChatCompletion> {
}
