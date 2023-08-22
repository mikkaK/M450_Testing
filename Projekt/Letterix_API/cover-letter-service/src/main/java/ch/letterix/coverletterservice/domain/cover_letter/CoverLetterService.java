package ch.letterix.coverletterservice.domain.cover_letter;

import ch.letterix.coverletterservice.core.generic.AbstractService;
import ch.letterix.coverletterservice.domain.response.entity.ChatCompletion;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CoverLetterService extends AbstractService<CoverLetter> {

    String getCoverLetter(CoverLetter coverLetter) throws JsonProcessingException;
}
