import { toast } from "react-toastify";
import {UpdateWorkerDto} from "../dtos/update-worker.dto";
import axios from "axios";
export class WorkerService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getWorker(id:number) {

        const url = `${this.apiUrl}/workers/${id}`
        let response = await axios.get(url)
        return response.data
    }

    async updateWorker(updateWorkerDto: UpdateWorkerDto) {
        const url = `${this.apiUrl}/workers`

        try {
            let response = await axios.put(url,updateWorkerDto)
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

export const workerService = new WorkerService()