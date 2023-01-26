import { PhoneIcon } from "@chakra-ui/icons"
import { Button, Divider, Flex, Icon, Spacer, Image, Tooltip } from "@chakra-ui/react"
import { FiLogOut, FiHome, FiSettings } from 'react-icons/fi'
import { BsFillCalendar2WeekFill, BsBookmarkCheckFill, BsFillCalendarPlusFill, BsClockHistory } from 'react-icons/bs'
import { CgProfile } from 'react-icons/cg'
import { MdOutlineBloodtype } from 'react-icons/md'
import { BiUserPlus } from 'react-icons/bi'
import image from "../../../logo.svg"
import { Navigate, NavLink, useNavigate } from "react-router-dom"
import { useAuthStore } from "../../../stores/auth-store/auth.store"
import { useEffect, useState } from "react"
import css from "./side-menu.module.css"


export const SideMenu = () => {

    const logout = useAuthStore(state => state.logout)
    const role = useAuthStore(state => state.role)
    const loggedUser = useAuthStore(state => state.loggedUser)
    const getLoggedUser = useAuthStore(state => state.getLoggedUser)
    const navigate = useNavigate()

    const [isAdministrator, setIsAdministrator] = useState(role === "ADMINISTRATOR")
    const [isRegular, setIsRegular] = useState(role === "REGULAR")
    const [isWorker, setIsWorker] = useState(role === 'WORKER')
    const handleLogout = () => {
        logout()
        navigate("/login")
    }

    useEffect(() => {
        getLoggedUser()
        setIsAdministrator(role === "ADMINISTRATOR")
        setIsRegular(role === "REGULAR")
        setIsWorker(role === "WORKER")
    }, [role])


    const handleProfile = () => {
        if (role == "REGULAR")
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

            <Flex flex={10} gap={8} alignItems="center" justifyContent="center" direction={'column'}>
                <Flex>
                    <Tooltip label={'Centers'}>
                        <NavLink to={'/centers'}>
                            <FiHome color="white" cursor='pointer' size={40} />
                        </NavLink>
                    </Tooltip>
                </Flex>
                {
                    (isAdministrator && role != null) &&
                    <>
                        <Flex>
                            <Tooltip label={'Users'}>
                                <NavLink to={'/users'}>
                                    <CgProfile color='white' cursor='pointer' size={40} onClick={handleProfile} />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Appointments'}>
                                <NavLink to={'/appointments'}>
                                    <BsFillCalendar2WeekFill color='white' size={40} cursor='pointer'></BsFillCalendar2WeekFill>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                    </>
                }
                {
                    (isRegular && role != null) &&
                    <>
                        <Flex>
                            <Tooltip label={'Appointments'}>
                                <NavLink to={'/appointments'}>
                                    <BsFillCalendar2WeekFill color='white' size={40} cursor='pointer'></BsFillCalendar2WeekFill>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'My Appointments'}>
                                <NavLink to={'/my-appointments'}>
                                    <BsBookmarkCheckFill color='white' size={40} cursor='pointer'></BsBookmarkCheckFill>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Surveys'}>
                                <NavLink to={'/survey'}>
                                    <MdOutlineBloodtype color="white" cursor='pointer' size={50} />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Surveys'}>
                                <NavLink to={'/my-past-appointments'}>
                                    <BsClockHistory color="white" cursor='pointer' size={40} />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                    </>
                }
                {
                    (isWorker && role != null) &&
                    <>
                        <Flex>
                            <Tooltip label={'Users'}>
                                <NavLink to={'/users'}>
                                    <CgProfile color='white' cursor='pointer' size={40} onClick={handleProfile} />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Create Appointment'}>
                                <NavLink to={'/appointments/create'}>
                                    <BsFillCalendarPlusFill color='white' size={40} cursor='pointer'></BsFillCalendarPlusFill>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Calendar'}>
                                <NavLink to={'/calendar-view'}>
                                    <BsFillCalendar2WeekFill color='white' size={40} cursor='pointer'></BsFillCalendar2WeekFill>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                    </>
                }
                {
                    role == null &&
                    <>
                        <Flex>
                            <Tooltip label={'Register'}>
                                <NavLink to={'/users/registration'}>
                                    <BiUserPlus color='white' size={50} cursor='pointer' />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Login'}>
                                <NavLink to={'/login'}>
                                    <CgProfile color='white' cursor='pointer' size={40} />
                                </NavLink>
                            </Tooltip>
                        </Flex>
                    </>
                }
                <Spacer />
                {
                    (role != null) &&
                    <>
                        <Flex background="green" onClick={(e) => navigate('/profile/update')} cursor='pointer' borderRadius={"50%"} color="white" width="50" minWidth="50" height="50" alignItems='center' justifyContent="center" fontSize='20'>
                            <Tooltip label="Profile">
                                <NavLink to="/profile/update">
                                    {loggedUser?.name[0]}
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Settings'}>
                                <NavLink to={'/settings'}>
                                    <FiSettings color='white' size={40} cursor='pointer'></FiSettings>
                                </NavLink>
                            </Tooltip>
                        </Flex>
                        <Flex>
                            <Tooltip label={'Logout'}>
                                <Flex>
                                    <FiLogOut color='white' size={40} cursor='pointer' onClick={handleLogout} />
                                </Flex>
                            </Tooltip>
                        </Flex>
                    </>
                }

            </Flex>
        </Flex >
    )
}


export default SideMenu