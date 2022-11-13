import { toast } from "react-toastify"
import { WorkerRegistrationDTO } from "../dtos/worker-registration.dto"
import axios from "axios"

export class WorkerService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenters() {
        const url = `${this.apiUrl}/centers/select`
        let response = await axios.get(url)
        return response.data
    }

    async registrate(registrationDto: WorkerRegistrationDTO) {
        const url = `${this.apiUrl}/workers`
        try {
            let response = await axios.post(url, registrationDto)
            toast.success("Succesfully registered!")
            return true
        } catch(e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, {autoClose: 3000})
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

export const workerService = new WorkerService()