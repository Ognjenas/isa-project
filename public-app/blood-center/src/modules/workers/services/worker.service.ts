import { toast } from "react-toastify"
import { WorkerRegistrationDTO } from "../dtos/worker-registration.dto"
import axios from "axios"
import { UpdateWorkerDto } from "../dtos/update-worker.dto"
import { getAxios } from "../../../util/axios-wrapper"

export class WorkerService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getBloodStorage(){
        const url = `${this.apiUrl}/workers/blood-storage`
        try {
            let response = await getAxios().get(url)
            return response.data
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async getWorkerCenter(){
        const url = `${this.apiUrl}/workers/center`
        try {
            let response = await getAxios().get(url)
            return response.data
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async getCenters() {
        try {
            const url = `${this.apiUrl}/centers/list`
            let response = await getAxios().get(url)
            return response.data.centers
        } catch (e: any) {
            toast.error(e.response.data)
        }

    }

    async registrate(registrationDto: WorkerRegistrationDTO) {
        const url = `${this.apiUrl}/workers`
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

    async getWorker(id: number) {

        const url = `${this.apiUrl}/workers/${id}`
        let response = await axios.get(url)
        return response.data
    }

    async updateWorker(updateWorkerDto: UpdateWorkerDto) {
        const url = `${this.apiUrl}/workers`

        try {
            let response = await axios.put(url, updateWorkerDto)
            toast.success("Successfully updated worker!", { autoClose: 3000 })
            return true
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }

    }

    async getEvents() {
        try {
            const url = `${this.apiUrl}/appointments/for-worker`
            let response = await getAxios().get(url)
            return response.data
        }
        catch (e: any) {
            const message = this.parseError(e.response.data)
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

export const workerService = new WorkerService()