import create, { StateCreator } from "zustand"
import { persist } from "zustand/middleware"
import { immer } from "zustand/middleware/immer"
import { AuthRequestDTO } from "../../modules/auth/dtos/authRequest.dto"
import { authService } from "../../modules/auth/services/auth.service"
import { AuthStoreActions } from "./interfaces/auth-store-actions"
import { AuthStoreState } from "./interfaces/auth-store-state"
import jwt_decode from "jwt-decode";

type AuthStore = AuthStoreActions & AuthStoreState

const state: AuthStoreState = {
    token: null,
    role: null,
    loggedUser: null
}

export const authStoreSlice: StateCreator<
    AuthStore,
    [["zustand/persist", unknown], ["zustand/immer", never]]
> = (set, get) => ({
    ...state,
    login: async (authRequest: AuthRequestDTO) => {
        const response = await authService.auth(authRequest)
        if (!response) return false
        let decoded = jwt_decode(response)
        // @ts-ignore
        let role = decoded.role
        set((state) => {
            state.token = response
            state.role = role
        })

        get().getLoggedUser()
        return true
    },
    register: () => { },
    logout: () => {
        set(state => {
            state.token = null
            state.role = null
        })
    },
    getLoggedUser: async () => {
        let logged = await authService.getLoggedUser()
        set((state) => {
            state.loggedUser = logged
        })
    }

})



export const useAuthStore = create<AuthStore>()(
    persist(immer(authStoreSlice), {
        name: "auth",
        partialize: (state) => ({ token: state.token, role: state.role }),
    })
)
