import { Sex } from "../../shared/utils/constants"
import { AddressDTO } from "./address.dto"

export interface RegistrationDTO {
    email: string
    password: string
    name: string
    surname: string
    sex: Sex
    uid: string
    profession: string
    school: string
    address: AddressDTO
}