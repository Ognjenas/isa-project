import { AuthRequestDTO } from "../../../modules/auth/dtos/authRequest.dto"

export interface AuthStoreActions {
    login: (authRequest: AuthRequestDTO) => Promise<boolean>
    register: () => void


}
