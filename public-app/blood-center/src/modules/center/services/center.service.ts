import { toast } from "react-toastify";
import { UpdateCenterDto } from "../dtos/update-center.dto";
import axios from "axios";
import { CenterRegistrationDTO } from "../dtos/center-registration.dto";
import {getAxios} from "../../../util/axios-wrapper";

export class CenterService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenter(id: number) {
        try {
        const url = `${this.apiUrl}/centers/${id}`
        let response = await getAxios().get(url)
        return response.data
        }catch (e: any) {
            console.log("OVO JE GRESKA")
            console.log(e.response.data.message)
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async updateCenter(updateCenterDto: UpdateCenterDto) {
        const url = `${this.apiUrl}/centers/`

        try {
            let response = await axios.put(url, updateCenterDto)
            toast.success("Successfully updated center!", { autoClose: 3000 })
            return true
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async registrate(registrationDto: CenterRegistrationDTO) {
        const url = `${this.apiUrl}/centers`

        try {
            let response = await axios.post(url, registrationDto)
            toast.success("Successfully updated center!", { autoClose: 3000 })
            return true
        } catch (e: any) {
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

export const centerService = new CenterService()