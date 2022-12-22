import {CreateBloodDonationDto} from "../components/create-donation/create-blood-donation-dto";
import axios from "axios";
import {toast} from "react-toastify";
import {getAxios} from "../../../util/axios-wrapper";


export class BloodDonationService{

    private apiUrl: string = "http://localhost:8000"
    constructor() { }

    async createBloodDonation(dto:CreateBloodDonationDto)
    {
        const url = `${this.apiUrl}/bloodDonation`

        try {
            let response = await getAxios().post(url, dto)
            toast.success("Successfully donated blood!", { autoClose: 3000 })
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

export const bloodDonationService =new BloodDonationService()