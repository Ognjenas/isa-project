import {
    Box,
    Button,
    Flex,
    FormLabel,
    Radio,
    RadioGroup,
    Stack,
} from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import TemplateForm from "../../shared/components/template-form"
import TemplateErrorRadio from "../../shared/components/template-form/components/template-error-radio"
import { AnswerDTO } from "../dtos/AnswerDTO"
import { MakeSurveyDTO } from "../dtos/MakeSurveyDTO"
import { SurveyQuestionDTO } from "../dtos/SurveyQuestionDTO"
import { surveyService } from "../services/survey.service"

const useAnswers = () => {
    const [answers, setAnswers] = useState<AnswerDTO[]>([])
    const navigate = useNavigate()

    const setAnswer = (questionId: number, value: string) => {
        let filtered = answers?.filter((a) => a.questionId !== questionId)
        filtered?.push({
            answer: value,
            questionId: questionId,
        })
        console.log(questionId, value, filtered)
        setAnswers(filtered)
    }

    return { answers, setAnswer }
}

export const MakeSurveyComponent = () => {
    const [questions, setQuestions] = useState<SurveyQuestionDTO[]>()
    const { answers, setAnswer } = useAnswers()
    const navigate = useNavigate()

    const handleOnMounted = async () => {
        let res = await surveyService.getQuestions()
        setQuestions(res)
    }

    const handleOnSubmit = async () => {
        const dto: MakeSurveyDTO = {
            answers: answers,
        }
        let ok = await surveyService.makeSurvey(dto)
        if (ok) {
            setTimeout(() => navigate("/"), 3000)
        }
    }

    useEffect(() => {
        handleOnMounted()
    }, [])

    return (
        <Flex
            flexDirection="column"
            margin="auto"
            borderRadius={10}
            justifyContent="center"
            width="100%"
            className="update-profile-form"
            border="1px solid lightgray"
            w={700}
            p={20}
            gap={3}
        >
            {questions?.map((question) => (
                <Flex
                    justifyContent="space-between"
                    border="1px solid"
                    padding="2"
                    backgroundColor="#effcf9"
                    key={question.id}
                >
                    <FormLabel>{question.question}</FormLabel>
                    <RadioGroup
                        onChange={(value) => setAnswer(question.id, value)}
                    >
                        <Stack direction="row">
                            <Radio value="true">True</Radio>
                            <Radio value="false">False</Radio>
                        </Stack>
                    </RadioGroup>
                </Flex>
            ))}

            <Flex justifyContent="flex-end" onClick={() => handleOnSubmit()}>
                <Button>Submit</Button>
            </Flex>
        </Flex>
    )
}

export default MakeSurveyComponent
