package com.isateam.blooddonationcenter.core.surveys;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "survey_questions")
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private Set<SurveyAnswer> answers;
}
