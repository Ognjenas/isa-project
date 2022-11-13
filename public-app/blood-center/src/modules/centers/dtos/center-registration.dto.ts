import { AddressDTO } from "../../auth/dtos/address.dto"

export interface CenterRegistrationDTO {
    name: string
    address: AddressDTO
    description: string
}