import { filter } from '@chakra-ui/react'
import axios from 'axios'
import { FilterSort } from '../dtos/filter-sort.dto'

export class CenterService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenters() {
        const url = `${this.apiUrl}/centers/list`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        let data = await response.json()
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

}

export const centerService = new CenterService()