import { toast } from "react-toastify";
import { UpdateProfileDTO } from "../dtos/update-profile.dto";
import axios from "axios"

export class ProfileService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getProfile() {
        const url = `${this.apiUrl}/users/1`

        try {
            let response = await axios.get(url)
            return response.data
        } catch(e: any) {
            toast.error("Something wrong with loading users", {autoClose: 300})
        }

    }

    async updateProfile(updateProfileDTO: UpdateProfileDTO, id: number) {
        const url = `${this.apiUrl}/users/${id}`

        try {
            let response = await axios.patch(url, updateProfileDTO)
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