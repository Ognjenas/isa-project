import { toast } from "react-toastify"
import { CenterRegistrationDTO } from "../dtos/center-registration.dto"
import axios from "axios"


export class CenterService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async registrate(registrationDto: CenterRegistrationDTO) {
        const url = `${this.apiUrl}/centers`

        try {
            let response = await axios.post(url, registrationDto)
            toast.success("Successfully updated worker!", { autoClose: 3000 })
            return true
        } catch (e: any) {
            const message=this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }

    }

    parseError(error: any) {
        if (error.statusCode) {
            return error.message
        } else {
            return 'Data not correct!'
        }
    }
}

export const centerService = new CenterService()