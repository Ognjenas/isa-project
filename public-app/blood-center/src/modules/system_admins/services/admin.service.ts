import { toast } from "react-toastify"
import { RegistrationDTO } from "../../auth/dtos/registration.dto"
import axios from "axios"
import { AdminRegistrationDTO } from "../dtos/admin-registration.dto"
import { getAxios } from "../../../util/axios-wrapper"

export class AdminService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async registrate(registrationDto: AdminRegistrationDTO) {
        const url = `${this.apiUrl}/system-admin`

        try {
            let response = await getAxios().post(url, registrationDto)
            toast.success("Succesfully registered!")
            return true
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async changePassword(password: string) {
        const url = `${this.apiUrl}/system-admin/change-password`

        try {
            let response = await getAxios().put(url, password)
            toast.success("Succesfully changed password!")
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

export const adminService = new AdminService()