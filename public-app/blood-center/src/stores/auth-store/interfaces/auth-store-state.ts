export interface AuthStoreState {
    token?: string | null
    role?: string | null
    loggedUser: LoggedUser | null
}


interface LoggedUser {
    name: string,
    surname: string
}