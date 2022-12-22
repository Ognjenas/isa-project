import { useEffect, useState } from "react"
import { Navigate, useNavigate } from "react-router-dom"
import { adminService } from "../../services/admin.service"

interface Props {
    children: JSX.Element
}

export const CheckFirstLoginAdmin = ({children}:Props) => {
    const [state,setState] = useState<boolean>(false);

    const handleOnMounted = async () => {
        let state = await adminService.checkFirstLogin()
        setState(state)
    }

    useEffect(() => {
        handleOnMounted()
    }, [])

    if(state){
        return  <Navigate to={"/password-change"} replace /> 
    }
    else 
        return children
}


export default CheckFirstLoginAdmin
