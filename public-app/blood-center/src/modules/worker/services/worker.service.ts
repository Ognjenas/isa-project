import { toast } from "react-toastify";
import {UpdateWorkerDto} from "../dtos/update-worker.dto";

export class WorkerService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getWorker(id:number) {

        const url = `${this.apiUrl}/workers/${id}`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        // 'Access-Control-Allow-Origin':'*'
            }
        })

        if (!response.ok) throw new Error("Worker doesn't exist!")
        let data = await response.json()
        return data
    }

    async updateWorker(updateWorkerDto: UpdateWorkerDto, id: number) {
        const url = `${this.apiUrl}/workers/${id}`

        try {
            let response = await fetch(url, {
                method: 'PATCH',
                body: JSON.stringify(updateWorkerDto),
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            if (!response.ok) {
                let error = await response.json()
                this.parseError(error)
            }
            toast.success("Successfully updated worker!", { autoClose: 3000 })
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

export const workerService = new WorkerService()