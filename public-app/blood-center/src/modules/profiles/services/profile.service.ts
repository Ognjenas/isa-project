import { toast } from "react-toastify";
import { UpdateProfileDTO } from "../dtos/update-profile.dto";


export class ProfileService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getProfile() {
        const url = `${this.apiUrl}/users/1`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        if (!response.ok) throw new Error("User doesn't exist!")
        let data = await response.json()
        return data
    }

    async updateProfile(updateProfileDTO: UpdateProfileDTO, id: number) {
        const url = `${this.apiUrl}/users/${id}`

        try {
            let response = await fetch(url, {
                method: 'PATCH',
                body: JSON.stringify(updateProfileDTO),
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            if (!response.ok) {
                let error = await response.json()
                this.parseError(error)
            }
            toast.success("Successfully updated profile!", { autoClose: 3000 })
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


export const profileService = new ProfileService()