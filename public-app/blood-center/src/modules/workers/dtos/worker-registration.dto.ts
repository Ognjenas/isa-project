import { RegistrationDTO } from "../../auth/dtos/registration.dto"

export interface WorkerRegistrationDTO {
    user: RegistrationDTO
    centerId: number
}
