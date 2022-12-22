import { Navigate } from "react-router-dom"
import { useAuthStore } from "../stores/auth-store/auth.store"

interface Props {
    roles: string[]
    children: JSX.Element
}

export const ProtectedWrapper = ({ roles, children }: Props) => {
    const role = useAuthStore((state) => state.role)

    if (!role) {
        return <Navigate to={"/"} replace />
    }

    if (!roles.includes(role)) {
        return <Navigate to="/" replace />
    }

    return children
}
