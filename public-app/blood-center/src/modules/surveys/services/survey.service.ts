import { toast } from "react-toastify"

export class SurveyService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getQuestions() {
        const url = `${this.apiUrl}/surveys`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        if (!response.ok) throw new Error("Surveys doesn't exist!")
        let data = await response.json()
        return data.questions
    }


    parseError(error: any) {
        if (error.statusCode) {
            throw new Error(error.message)
        } else {
            throw new Error('Date not correct!')
        }
    }
}


export const surveyService = new SurveyService()