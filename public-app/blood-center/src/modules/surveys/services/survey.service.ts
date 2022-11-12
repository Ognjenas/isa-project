import { toast } from "react-toastify"
import { MakeSurveyDTO } from "../dtos/MakeSurveyDTO"

export class SurveyService {
    private apiUrl: string = "http://localhost:8000"
    constructor() {}

    async getQuestions() {
        const url = `${this.apiUrl}/surveys`

        let response = await fetch(url, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        })

        if (!response.ok) throw new Error("Surveys doesn't exist!")
        let data = await response.json()
        return data.questions
    }

    async makeSurvey(MakeSurveyDTO: MakeSurveyDTO) {
        const url = `${this.apiUrl}/surveys`

        try {
            let response = await fetch(url, {
                method: "POST",
                body: JSON.stringify(MakeSurveyDTO),
                headers: {
                    "Content-Type": "application/json",
                },
            })

            if (!response.ok) {
                let error = await response.json()
                this.parseError(error)
            }
            toast.success("Successfully made survey!", { autoClose: 3000 })
            return true
        } catch (e: any) {
            toast.error(e.message, { autoClose: 3000 })
            return false
        }
    }

    parseError(error: any) {
        if (error.statusCode) {
            throw new Error(error.message)
        } else {
            throw new Error("Date not correct!")
        }
    }
}

export const surveyService = new SurveyService()
