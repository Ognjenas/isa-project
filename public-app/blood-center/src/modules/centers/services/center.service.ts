import { toast } from "react-toastify"
import { CenterRegistrationDTO } from "../dtos/center-registration.dto"

export class CenterService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async registrate(registrationDto: CenterRegistrationDTO) {
        const url = `${this.apiUrl}/centers`

        try {
            let response = await fetch(url, {
                method: 'POST',
                body: JSON.stringify(registrationDto),
                headers: {
                    'Content-Type': 'application/json'
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
            throw new Error('Something went wrong :(')
        }
    }
}

export const centerService = new CenterService()