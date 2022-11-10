import { Flex, Image, Heading } from "@chakra-ui/react"
import { NavLink } from "react-router-dom"
import logo from "../../../../logo.svg"

export const ErrorComponent = () => {


    return (
        <Flex justifyContent='center' alignItems="center" direction='column' height='100vh'>
            <Image
                src={logo}
                boxSize={80}
            />
            <Heading textAlign={'center'} as='h1'>Ups! Wrong place to go!</Heading>
            <NavLink to={'/'}><Heading as='h2' textDecoration={'underline'}>Back Home!</Heading></NavLink>



        </Flex>
    )
}


export default ErrorComponent