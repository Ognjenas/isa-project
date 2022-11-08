import { Sex } from "../../shared/utils/constants"
import { Address } from "../model/address"

export interface UpdateProfileDTO {
    name: string
    surname: string
    sex: Sex
    uid: string
    profession: string
    school: string
    address: Address
}