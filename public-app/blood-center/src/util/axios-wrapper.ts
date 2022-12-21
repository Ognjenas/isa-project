import axios from "axios"
import { useAuthStore } from "../stores/auth-store/auth.store"

const defaultHeaders = {
    accept: "application/json",
    "Content-Type": "application/json",
}

export function getAxios() {
    const token = useAuthStore.getState().token
    return axios.create({
        baseURL: "http://localhost:8000",
        headers: {
            ...defaultHeaders,
            Authorization: `Bearer ${token}`,
        },
    })
}

export {}
