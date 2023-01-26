import { filter } from '@chakra-ui/react'
import axios from 'axios'
import { FilterSort } from '../dtos/filter-sort.dto'
import { toast } from "react-toastify"
import { getAxios } from "../../../../../util/axios-wrapper";
import moment from 'moment';

export class CenterService {

    private url: string = "http://localhost:8000"
    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenters() {
        const url = `${this.apiUrl}/centers/list`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        let data = await response.json()
        console.log(data.centers)
        return data.centers
    }

    // async getCentersSorted(field: string, sort: string) {
    //     const url = `${this.apiUrl}/centers/${field}/${sort}`

    //     let response = await fetch(url, {
    //         method: 'GET',
    //         headers: { 'Content-Type': 'application/json' }
    //     })

    //     let data = await response.json()
    //     return data.centers
    // }


    async getCentersFiltered(filters?: FilterSort) {
        const url = `${this.apiUrl}/centers/list`

        try {
            let response = await axios.get(url, {
                params: this.createFilters(filters)
            })
            return response.data

        } catch (e: any) {
            console.log(e)
        }
    }

    createFilters(filters?: FilterSort) {

        if (!filters) return {}
        let params: any = {}
        if (filters.sort && filters.sortBy) {
            params = { sort: filters.sort, sortBy: filters.sortBy }
        }

        if (filters.filterBy.trim() != '')
            params[filters.filterBy] = filters.filterByValue
        return params
    }

    async getCentersPatient(startTime: Date) {
        try {
            const formated = moment(startTime).format("YYYY-MM-DD HH:mm:ss")
            const url = `${this.url}/centers/by-time/${formated}`
            const response = await getAxios().get(url)
            return response.data.centers
        } catch (e) {
            const message = this.parseError(e)
            toast.error(message)
            return []
        }
    }

    parseError(error: any) {
        if (error.statusCode)
            return error.message
        return error.response.data.message
    }


}

export const centerService = new CenterService()