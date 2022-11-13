import {toast} from "react-toastify";
import {UpdateCenterDto} from "../dtos/update-center.dto";
import {UpdateWorkerDto} from "../../worker/dtos/update-worker.dto";

export class CenterService{

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenter(id:number) {
        const url = `${this.apiUrl}/centers/${id}`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        if (!response.ok) throw new Error("Center doesn't exist!")
        let data = await response.json()
        return data
    }

    async updateCenter(updateCenterDto: UpdateCenterDto) {
        const url = `${this.apiUrl}/centers/`

        try {
            let response = await fetch(url, {
                method: 'PUT',
                body: JSON.stringify(updateCenterDto),
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            if (!response.ok) {
                let error = await response.json()
                this.parseError(error)
            }
            toast.success("Successfully updated center!", { autoClose: 3000 })
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

export const centerService= new CenterService()