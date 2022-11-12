import { SearchUsersDTO } from "../dtos/search-users.dto";
import { toast } from "react-toastify"


export class UserService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getUsers(searchUsersDTO: SearchUsersDTO) {

        const url = `${this.apiUrl}/users/search`

        try {
            let response = await fetch(url, {
                method: 'POST',
                body: JSON.stringify(searchUsersDTO),
                headers: { 'Content-Type': 'application/json' }
            })

            if (!response.ok) {
                let error = await response.json()
                this.parseError(error)
            }
            let data = await response.json()
            return data
        } catch (e: any) {
            toast.error(e.message, { autoClose: 3000 })
            return false
        }

    }

    parseError(error: any) {
        if (error.statusCode) {
            throw new Error(error.message)
        } else {
            throw new Error('Something went wrong :(')
        }
    }
}


export const usersService = new UserService()