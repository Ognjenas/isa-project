import { Sex } from "../../shared/utils/constants"
import { AddressDTO } from "./address.dto"

export interface RegistrationDTO {
    name: string
    surname: string
    sex: Sex
    uid: string
    profession: string
    school: string
    address: AddressDTO
}