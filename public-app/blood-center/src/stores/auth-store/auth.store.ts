import create, { StateCreator } from "zustand";
import { persist } from "zustand/middleware";
import { immer } from "zustand/middleware/immer";
import { AuthStoreActions } from "./interfaces/auth-store-actions";
import { AuthStoreState } from "./interfaces/auth-store-state";


type AuthStore = AuthStoreActions & AuthStoreState

const state: AuthStoreState = {
    token: ""
}


export const authStoreSlice:
    StateCreator<
        AuthStore,
        [
            ["zustand/persist", unknown],
            ["zustand/immer", never]
        ]
    > = (set, get) => ({
        ...state,
        login: () => { },
        register: () => { }
    })


export const useAuthStore = create<AuthStore>()(
    persist(immer(authStoreSlice), {
        name: "auth",
        partialize: (state) => ({ token: state.token }),
    })
);