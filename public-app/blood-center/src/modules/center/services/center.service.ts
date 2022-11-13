import {toast} from "react-toastify";
import {UpdateCenterDto} from "../dtos/update-center.dto";
import {UpdateWorkerDto} from "../../worker/dtos/update-worker.dto";
import axios from "axios";

export class CenterService{

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenter(id:number) {
        const url = `${this.apiUrl}/centers/${id}`
        let response = await axios.get(url)
        return response.data
    }

    async updateCenter(updateCenterDto: UpdateCenterDto) {
        const url = `${this.apiUrl}/centers/`

        try {
            let response = await axios.put(url,updateCenterDto)
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

export const centerService= new CenterService()