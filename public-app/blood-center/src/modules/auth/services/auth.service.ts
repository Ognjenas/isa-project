import { toast } from "react-toastify"
import { RegistrationDTO } from "../dtos/registration.dto"

export class AuthService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async registrate(registrationDto: RegistrationDTO) {
        const url = `${this.apiUrl}/users`

        try {let response = await fetch(url, {
            method: 'POST',
            body: JSON.stringify(registrationDto),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : '*'
            }
        })

        if (!response.ok) {
            let error = await response.json()
            this.parseError(error)
        }
            toast.success("Successfully registered!", { autoClose: 3000 })
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
            throw new Error('Date not correct!')
        }
    }
}


export const authService = new AuthService()