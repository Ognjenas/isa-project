import { SearchUsersDTO } from "../dtos/search-users.dto";
import { toast } from "react-toastify"
import axios from "axios"
import {getAxios} from "../../../util/axios-wrapper";


export class UserService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getUsers(searchUsersDTO: SearchUsersDTO) {
        const url = `${this.apiUrl}/users/search`
        let response = await getAxios().post(url, searchUsersDTO)
        return response.data

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