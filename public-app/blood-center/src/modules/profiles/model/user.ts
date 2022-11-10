import { Address } from "./address"

export interface User {
    id: number
    email: string
    name: string
    surname: string
    uid: string
    profession: string
    school: string
    address: Address
}