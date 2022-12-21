import { toast } from "react-toastify"
import axios from "axios"
import { MakeSurveyDTO } from "../dtos/MakeSurveyDTO"
import { getAxios } from "../../../util/axios-wrapper"

export class SurveyService {
    private apiUrl: string = "http://localhost:8000"

    constructor() {}

    async getQuestions() {
        const url = `${this.apiUrl}/surveys`

        try {
            let response = await getAxios().get(url)
            return response.data.questions
        } catch (e: any) {
            toast.error("Something wrong with capturing questions!")
            return []
        }
    }

    async makeSurvey(makeSurveyDTO: MakeSurveyDTO) {
        const url = `${this.apiUrl}/surveys`

        try {
            let response = await getAxios().post(url, makeSurveyDTO)
            toast.success("Successfully made survey!", { autoClose: 3000 })
            return true
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    parseError(error: any) {
        if (error.statusCode) {
            return error.message
        } else {
            return "Data not correct!"
        }
    }
}

export const surveyService = new SurveyService()
