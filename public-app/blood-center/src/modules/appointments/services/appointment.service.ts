import moment from "moment";
import { getAxios } from "../../../util/axios-wrapper";
import { toast } from "react-toastify"



export class AppointmentService {

    private url: string = "http://localhost:8000"
    constructor() {

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

    parseError(error: any) {
        if (error.statusCode) {
            return error.message
        } else {
            return "Data not correct!"
        }
    }
}



export const appointmentService = new AppointmentService()