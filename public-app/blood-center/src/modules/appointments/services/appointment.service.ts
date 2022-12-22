import moment from "moment";
import { getAxios } from "../../../util/axios-wrapper";
import { toast } from "react-toastify"



export class AppointmentService {

    private url: string = "http://localhost:8000"
    constructor() {

    }

    async getMyAppointments() {
        try {
            const url = `${this.url}/appointments/by-user`
            const response = await getAxios().get(url)
            return response.data
        } catch (e) {
            const message = this.parseError(e)
            toast.error(message)
            return []
        }
    }

    async getFreeAppointmentsForDateTime(datetime: Date) {
        try {
            const formated = moment(datetime).format("YYYY-MM-DD HH:mm:ss")
            const url = `${this.url}/appointments/date/${formated}/free`
            const response = await getAxios().get(url)
            return response.data
        } catch (e) {
            const message = this.parseError(e)
            toast.error(message)
            return []
        }
    }

    async getFreeAppointments(centerId: number, sort: string) {
        try {
            const url = `${this.url}/appointments/${centerId}/free`
            const response = await getAxios().get(url, { params: { sortby: sort } })
            return response.data.appointments
        } catch (e) {
            const message = this.parseError(e)
            toast.error(message)
            return []
        }
    }

    async reserveAppointment(appointmentId: number, userId: number) {
        try {
            const url = `${this.url}/appointments/${appointmentId}/user/${userId}`
            const response = await getAxios().patch(url)
            if (response.status == 200) return true;
        } catch (e: any) {
            const message = this.parseError(e.response.data)
            toast.error(message, { autoClose: 3000 })
            return false
        }
    }

    async cancelAppointment(appointmentId: number) {
        try {
            const url = `${this.url}/appointments/cancel/${appointmentId}`
            const response = await getAxios().patch(url)
            if (response.status == 200) return true;
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
            return error.response.data
        }
    }
}



export const appointmentService = new AppointmentService()