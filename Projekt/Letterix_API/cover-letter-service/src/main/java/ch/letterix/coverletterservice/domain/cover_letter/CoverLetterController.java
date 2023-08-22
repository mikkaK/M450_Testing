package ch.letterix.coverletterservice.domain.cover_letter;

import ch.letterix.coverletterservice.domain.response.entity.ChatCompletion;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cover-letter")
public class CoverLetterController {
    private final CoverLetterService coverLetterService;
    public CoverLetterController(CoverLetterService coverLetterService) {
        this.coverLetterService = coverLetterService;
    }

    @PostMapping("")
    public ResponseEntity<String> newCoverLetter(@Valid @RequestBody CoverLetter coverLetter) throws JsonProcessingException {
        return new ResponseEntity<>(coverLetterService.getCoverLetter(coverLetter), HttpStatus.CREATED);
    }
}
