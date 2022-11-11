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
@Table(name = "survey_possible_answers")
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable=false)
    private SurveyQuestion question;

    private String answer;

    @ManyToMany(mappedBy = "answers")
    private Set<Survey> surveys;
}
