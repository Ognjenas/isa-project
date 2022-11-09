import { RegistrationDTO } from "../dtos/registration.dto"

export class AuthService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async registrate(registrationDto: RegistrationDTO) {
        const url = `${this.apiUrl}/users`

        let response = await fetch(url, {
            method: 'POST',
            body: JSON.stringify(registrationDto),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : '*'
            }
        })

        if (!response.ok) throw new Error("Something wrong with registrating profile")
    }
}


export const authService = new AuthService()