import { PhoneIcon } from "@chakra-ui/icons"
import { Button, Divider, Flex, Icon, Spacer, Image } from "@chakra-ui/react"
import { FiLogOut, FiHome, FiSettings } from 'react-icons/fi'
import { CgProfile } from 'react-icons/cg'
import { MdOutlineBloodtype } from 'react-icons/md'
import image from "../../../logo.svg"
import { NavLink } from "react-router-dom"



export const SideMenu = () => {
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
                <NavLink to={'/home'}>
                    <FiHome color="white" cursor='pointer' size={40} />
                </NavLink>
                <NavLink to={'/profile'}>
                    <CgProfile color='white' cursor='pointer' size={40} />
                </NavLink>
                <NavLink to={'/storage'}>
                    <MdOutlineBloodtype color="white" cursor='pointer' size={50} />
                </NavLink>
                <Spacer />
                <NavLink to={'/settings'}>
                    <FiSettings color='white' size={40} cursor='pointer'></FiSettings>
                </NavLink>
                <FiLogOut color='white' size={40} cursor='pointer' />
            </Flex>
        </Flex>
    )
}


export default SideMenu