import { toast } from "react-toastify"
import { RegistrationDTO } from "../dtos/registration.dto"
import axios from "axios"
import { AuthRequestDTO } from "../dtos/authRequest.dto"
import { getAxios } from "../../../util/axios-wrapper"

export class AuthService {
    private apiUrl: string = "http://localhost:8000"
    constructor() {}

    async registrate(registrationDto: RegistrationDTO) {
        const url = `${this.apiUrl}/users`

        try {
            let response = await axios.post(url, registrationDto)
            toast.success("Succesfully registered!")
            return true
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async auth(authRequest: AuthRequestDTO) {
        const url = `${this.apiUrl}/auth`

        try {
            let response = await axios.post(url, authRequest)
            toast.success("Succesfully logged!")
            return response.data
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return null
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

export const authService = new AuthService()
