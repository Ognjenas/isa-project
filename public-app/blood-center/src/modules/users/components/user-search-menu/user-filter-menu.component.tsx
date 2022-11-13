import { Flex, FormControl, FormLabel, Grid, GridItem, Input, Button, Box } from "@chakra-ui/react"
import { useState } from "react"
import { SearchUsersDTO } from "../../dtos/search-users.dto"


interface Props {
    onSubmit: (params: SearchUsersDTO) => void
}

export const UserSearchMenu = ({onSubmit}: Props) => {

    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")

    const handleSubmit = (e: any) => {
        const params: SearchUsersDTO = { name, surname}
        onSubmit(params)
    }


    return (
        <Flex 
            width="100%"
            paddingLeft="20px"
            height="80px"
            position="sticky"
            top={0}
            left={0}
            boxShadow="base"
            backgroundColor="#2e3034"
            alignItems="center"
            justifyContent={"flex-start"}
            gap={15}
        >
            <Box>
                <Input
                    placeholder="Search by name"
                    bg={"white"}
                    onChange={(e) => setName(e.target.value)}
                    value={name} />
            </Box>
            <Box>
                <Input
                    placeholder="Search by surname"
                    bg={"white"}
                    onChange={(e) => setSurname(e.target.value)}
                    value={surname} />
            </Box>

            <Button colorScheme='blue' onClick={handleSubmit}>
                Search
            </Button>
    </Flex>
    )
}