export class CenterService {

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async getCenters() {
        const url = `${this.apiUrl}/centers`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        let data = await response.json()
        return data.centers
    }

    async getCentersSorted(field: string, sort: string) {
        const url = `${this.apiUrl}/centers/${field}/${sort}`

        let response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })

        let data = await response.json()
        return data.centers
    }


}

export const centerService = new CenterService()