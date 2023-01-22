import { toast } from "react-toastify";
import { UpdateProfileDTO } from "../dtos/update-profile.dto";
import axios from "axios"
import { getAxios } from "../../../util/axios-wrapper";

export class ProfileService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getProfile() {
        const url = `${this.apiUrl}/users/me`

        try {
            let response = await getAxios().get(url)
            return response.data
        } catch (e: any) {
            console.log(e)
            toast.error("Something wrong with loading users", { autoClose: 300 })
        }

    }

    async updateProfile(updateProfileDTO: UpdateProfileDTO, id: number) {
        const url = `${this.apiUrl}/users`

        try {
            let response = await getAxios().patch(url, updateProfileDTO)
            toast.success("Successfully updated profile!", { autoClose: 3000 })
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


export const profileService = new ProfileService()