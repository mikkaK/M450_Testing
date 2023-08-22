package ch.letterix.coverletterservice.domain.cover_letter;

import ch.letterix.coverletterservice.core.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cover_letter")
@JsonIgnoreProperties(value = { "id" })
public class CoverLetter extends AbstractEntity {

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name must be less than 30 characters")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname is mandatory")
    @Size(max = 30, message = "Surname must be less than 30 characters")
    private String surname;

    @Column(name="age")
    @Size(min = 1, max = 3, message = "Age must be between 1 and 3 characters")
    @NotBlank(message = "Age is mandatory")
    private String age;

    @Column(name = "applied_company")
    @NotBlank(message = "Applied company is mandatory")
    private String appliedCompany;

    @Column(name = "position")
    @NotBlank(message = "Position is mandatory")
    private String position;

    @Column(name = "current_education_level")
    @NotBlank(message = "Education level is mandatory")
    private String currentEducationLevel;

    @Column(name = "skills")
    @NotNull(message = "Skills are mandatory")
    private List<String> skills;

    @Column(name = "interests")
    @NotNull(message = "Interests are mandatory")
    private List<String> interests;

    @Column(name = "weaknesses")
    @NotNull(message = "Weaknesses are mandatory")
    private List<String> weaknesses;

    public CoverLetter() {
    }

    public CoverLetter(UUID id, String name, String surname, String age, String appliedCompany, String position, String currentEducationLevel, List<String> skills, List<String> interests, List<String> weaknesses) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.appliedCompany = appliedCompany;
        this.position = position;
        this.currentEducationLevel = currentEducationLevel;
        this.skills = skills;
        this.interests = interests;
        this.weaknesses = weaknesses;
    }
}
