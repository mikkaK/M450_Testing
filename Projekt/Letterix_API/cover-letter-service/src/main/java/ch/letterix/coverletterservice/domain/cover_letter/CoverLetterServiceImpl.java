package ch.letterix.coverletterservice.domain.cover_letter;

import ch.letterix.coverletterservice.core.generic.AbstractRepository;
import ch.letterix.coverletterservice.core.generic.AbstractServiceImpl;
import ch.letterix.coverletterservice.domain.response.ChatCompletionService;
import ch.letterix.coverletterservice.domain.response.entity.ChatCompletion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
@Log4j2
public class CoverLetterServiceImpl extends AbstractServiceImpl<CoverLetter> implements CoverLetterService {

    @Value("${letterix.OPENAI_API_KEY}")
    private String OPENAI_API_KEY;
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();
    private final CoverLetterRepository coverLetterRepository;
    private final ChatCompletionService chatCompletionService;
    @Autowired
    protected CoverLetterServiceImpl(AbstractRepository<CoverLetter> repository, CoverLetterRepository coverLetterRepository, ChatCompletionService chatCompletionService) {
        super(repository);
        this.coverLetterRepository = coverLetterRepository;
        this.chatCompletionService = chatCompletionService;
    }

    @Override
    public String getCoverLetter(CoverLetter coverLetter) throws JsonProcessingException {
        CoverLetter savedCoverLetter = saveCoverLetter(coverLetter);

        String prompt = buildPrompt(coverLetter);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (!Objects.equals(OPENAI_API_KEY.toLowerCase(), "notFound".toLowerCase())){
            log.debug("API Key found");
            log.debug("API Key: " + OPENAI_API_KEY);
        } else {
            log.error("API Key not found");
        }

        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String requestJson = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\""+ prompt +"\"}]}";

        HttpEntity<?> request = new HttpEntity<>(requestJson, headers);

        log.debug("Request: " + requestJson);

        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        log.debug("Response: " + response.getBody() );

        //TODO handle openAI service 503 not available
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        ChatCompletion completion = mapper.readValue(response.getBody(), ChatCompletion.class);
        completion.setCoverLetter(savedCoverLetter);
        chatCompletionService.save(completion);
        return response.getBody();
    }

    public CoverLetter saveCoverLetter (CoverLetter coverLetter){

        return coverLetterRepository.save(coverLetter);
    }

    public String buildPrompt(CoverLetter promptObject){
        log.debug("Started to build prompt with following attributes: " +
                "\n name: " + promptObject.getName() +
                ", \n surname: " + promptObject.getSurname() +
                ", \n age: " + promptObject.getAge() +
                ", \n currentEducationLevel: " + promptObject.getCurrentEducationLevel() +
                ", \n interests: " + promptObject.getInterests() +
                ", \n skills: " + promptObject.getSkills() +
                ", \n weaknesses: " + promptObject.getWeaknesses() +
                ", \n position: " + promptObject.getPosition() +
                ", \n appliedCompany: " + promptObject.getAppliedCompany());

        return "Erstelle ein Bewerbungsschreiben für einen " +
                promptObject.getAge()+
                "jährigen Schweizer " +
                promptObject.getCurrentEducationLevel() +
                ", der sich um eine Lehrstelle als " +
                promptObject.getPosition() +
                " bei " +
                promptObject.getAppliedCompany() +
                " bewirbt. Benutze diese Informationen des Schülers, um das Schreiben zu personalisieren:" +
                " Name:" +
                promptObject.getName() + promptObject.getSurname() +
                ", Alter:" +
                promptObject.getAge() +
                ", Hobbys:" +
                promptObject.getInterests() +
                ", Stärken:" +
                promptObject.getSkills() +
                ", Schwächen:" +
                promptObject.getWeaknesses() +
                ". Recherchiere über das angegebene Unternehmen (" +
                promptObject.getAppliedCompany() +
                ") und füge Details über" +
                promptObject.getAppliedCompany() +
                "in den Brief ein, damit er auf " +
                promptObject.getAppliedCompany() +
                "zugeschnitten ist. Verwende die nachfolgenden Styleregeln und Grammatik Anweisungen: Ersetze alle Schwächen durch weniger negativ klingende Synonyme; Der Brief sollte nicht perfekt sein und kann sogar einen Fehler enthalten; Da der Brief von einem jungen Menschen geschrieben wird, sollte er keine komplexe oder schwierige Wörter enthalten; Es darf nie 3 mal hintereinander der gleiche Satzanfang verwendet werden; Verwende keine ß.";

    }
}
