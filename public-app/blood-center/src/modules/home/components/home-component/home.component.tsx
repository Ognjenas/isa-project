import { Box, Flex } from "@chakra-ui/react"
import { useAuthStore } from "../../../../stores/auth-store/auth.store"



export const HomeComponent = () => {

    return (
        <Flex direction='column' justifyContent='flex-start' alignItems='flex-start'>
            <Box>Sta ima</Box>
            <Box>Sta nema</Box>
        </Flex>
    )
}

export default HomeComponent