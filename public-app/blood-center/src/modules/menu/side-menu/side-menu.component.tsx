import { PhoneIcon } from "@chakra-ui/icons"
import { Button, Divider, Flex, Icon, Spacer, Image } from "@chakra-ui/react"
import { FiLogOut, FiHome, FiSettings } from 'react-icons/fi'
import { BsFillCalendar2WeekFill, BsBookmarkCheckFill, BsFillCalendarPlusFill} from 'react-icons/bs'
import { CgProfile } from 'react-icons/cg'
import { MdOutlineBloodtype } from 'react-icons/md'
import image from "../../../logo.svg"
import { Navigate, NavLink, useNavigate } from "react-router-dom"
import { useAuthStore } from "../../../stores/auth-store/auth.store"
import { useEffect, useState } from "react"



export const SideMenu = () => {

    const logout = useAuthStore(state => state.logout)
    const role = useAuthStore(state => state.role)
    const navigate = useNavigate()

    const [isAdministrator, setIsAdministrator] = useState(role === "ADMINISTRATOR")
    const handleLogout = () => {
        logout()
        navigate("/login")
    }

    useEffect(() => {
        setIsAdministrator(role === "ADMINISTRATOR")
    }, [role])


    const handleProfile = () => {
        if(role == "REGULAR")
            navigate('/survey')
    }

    return (
        <Flex
            width='100px'
            height='100vh'
            maxH='100vh'
            paddingBottom='20px'
            direction='column'
            boxShadow={'lg'}
            alignItems={'center'}
            bg={'#28242c'}
        >
            <Flex flex={1} alignItems="center" justifyContent="center" >
                <Image
                    height='100%'
                    width='100%'
                    borderRadius={'full'}
                    src={image} />
            </Flex>
            <Flex flex={10} gap={10} alignItems="center" justifyContent="center" direction={'column'}>
                <NavLink to={'/centers'}>
                    <FiHome color="white" cursor='pointer' size={40} />
                </NavLink>
                <NavLink to={'/users'}>
                    <CgProfile color='white' cursor='pointer' size={40} onClick={handleProfile} />
                </NavLink>
                <NavLink to={'/survey'}>
                    <MdOutlineBloodtype color="white" cursor='pointer' size={50} />
                </NavLink>
            {
                (!isAdministrator && role != null)
                &&

                (<>
                    <NavLink to={'/appointments'}>
                        <BsFillCalendar2WeekFill color='white' size={40} cursor='pointer'></BsFillCalendar2WeekFill>
                    </NavLink>
                    <NavLink to={'/my-appointments'}>
                        <BsBookmarkCheckFill color='white' size={40} cursor='pointer'></BsBookmarkCheckFill>
                    </NavLink>
                </>)

                }

                {
                    (isAdministrator  && role != null) &&
                    <NavLink to={'/appointments/create'}>
                        <BsFillCalendarPlusFill color='white' size={40} cursor='pointer'></BsFillCalendarPlusFill>
                    </NavLink>
                }



                <Spacer />
                <NavLink to={'/settings'}>
                    <FiSettings color='white' size={40} cursor='pointer'></FiSettings>
                </NavLink>

                <FiLogOut color='white' size={40} cursor='pointer' onClick={handleLogout} />
            </Flex>
        </Flex>
    )
}


export default SideMenu