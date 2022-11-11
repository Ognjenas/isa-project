import { Box, Flex, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import TemplateForm from "../../shared/components/template-form"
import TemplateErrorRadio from "../../shared/components/template-form/components/template-error-radio"
import { AnswerDTO } from "../dtos/AnswerDTO"
import { SurveyQuestionDTO } from "../dtos/SurveyQuestionDTO"
import { surveyService } from "../services/survey.service"

const useAnswers = () => {
    const [answers, setAnswers] = useState<AnswerDTO[]>([])

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

    const handleOnMounted = async () => {
        let res = await surveyService.getQuestions()
        setQuestions(res)
    }

    useEffect(() => {
        handleOnMounted()
    }, [])

    const handleSubmit = () => {}

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
        >
            {questions?.map((question) => (
                <RadioGroup onChange={(value) => setAnswer(question.id, value)}>
                    {question.question + question.id}
                    <Stack direction="row">
                        <Radio value="true">True</Radio>
                        <Radio value="false">False</Radio>
                    </Stack>
                </RadioGroup>
            ))}
            <Flex>{JSON.stringify(answers)}</Flex>
        </Flex>
    )
}

export default MakeSurveyComponent
