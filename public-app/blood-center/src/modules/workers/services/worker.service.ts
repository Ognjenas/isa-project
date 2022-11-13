import { toast } from "react-toastify"
import { WorkerRegistrationDTO } from "../dtos/worker-registration.dto"

export class WorkerService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenters() {
        const url = `${this.apiUrl}/centers/select`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        let data = await response.json()
        return data
    }

    async registrate(registrationDto: WorkerRegistrationDTO) {
        const url = `${this.apiUrl}/workers`

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
            return error.message
        } else {
            return 'Data not correct!'
        }
    }
}

export const workerService = new WorkerService()