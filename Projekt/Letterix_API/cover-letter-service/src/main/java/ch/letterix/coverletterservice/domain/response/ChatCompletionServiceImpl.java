package ch.letterix.coverletterservice.domain.response;

import ch.letterix.coverletterservice.core.generic.AbstractRepository;
import ch.letterix.coverletterservice.core.generic.AbstractServiceImpl;
import ch.letterix.coverletterservice.domain.response.entity.ChatCompletion;
import org.springframework.stereotype.Service;

@Service
public class ChatCompletionServiceImpl extends AbstractServiceImpl<ChatCompletion> implements ChatCompletionService {
    protected ChatCompletionServiceImpl(AbstractRepository<ChatCompletion> repository) {
        super(repository);
    }
}
