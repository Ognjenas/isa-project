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

    async updateProfile(updateProfileDTO: UpdateProfileDTO) {
        const url = `${this.apiUrl}/users`

        let response = await fetch(url, {
            method: 'PATCH',
            body: JSON.stringify(updateProfileDTO),
            headers: {
                'Content-Type': 'application/json'
            }
        })

        if (!response.ok) throw new Error("Something wrong with updating profile")
    }
}


export const profileService = new ProfileService()