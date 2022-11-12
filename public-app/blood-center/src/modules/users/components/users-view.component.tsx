import { Table, Thead, Tbody, Tr, Th, Td, TableCaption, TableContainer, Flex, Grid, GridItem, Input, FormControl, FormLabel, Button } from "@chakra-ui/react"
import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { SearchUsersDTO } from "../dtos/search-users.dto"
import { usersService } from "../services/users.service"
import TemplateForm from "../../../modules/shared/components/template-form"
import { User } from "../../profiles/model/user"
import styles from "./users-view.module.css"
import { useScroll } from "framer-motion"


export const UsersView = () => {

    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    let user1: User = { id: -1, email: "", name: "", surname: "", uid: "", profession: "", school: "", address: { id: -1, city: "", country: "", street: "", number: "" } }
    const [users, setUsers] = useState([user1]);

    const starterSearch: SearchUsersDTO = { name, surname }

    const handleOnMounted = async () => {
        const searchedUsers: [User] = await usersService.getUsers(starterSearch)
        setUsers(searchedUsers)
    }

    const handleSubmit = async () => {
        const dto: SearchUsersDTO = {
            name,
            surname
        }
        const searchedUsers: [User] = await usersService.getUsers(dto)
        setUsers(searchedUsers)
    }


    useEffect(() => {
        handleOnMounted()
    }, [])

    return (
        <div className={styles["aligner"]}>

            <Flex margin='auto' justifyContent='center' width='100%' className="register-center-form" border='1px solid lightgray' w={700} height={200} p={1}>
                <FormControl>
                    <FormLabel>Search by</FormLabel>
                    <Grid templateColumns='repeat(2, 1fr)' templateRows='repeat(2, 1fr)' gap={5} width="100%">
                        <GridItem>
                            <label>By name</label>
                            <Input
                                onChange={(e) => setName(e.target.value)}
                                value={name} />
                        </GridItem>
                        <GridItem>
                            <label>By surname</label>
                            <Input
                                onChange={(e) => setSurname(e.target.value)}
                                value={surname} />
                            <Button onClick={handleSubmit}>
                                Search
                            </Button>
                        </GridItem>
                    </Grid>
                </FormControl>
            </Flex>
            <TableContainer>
                <Table variant='simple'>
                    <TableCaption>Users view</TableCaption>
                    <Thead>
                        <Tr>
                            <Th>Name</Th>
                            <Th>Surname</Th>
                            <Th>Email</Th>
                            <Th>Profession</Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {users.map(user =>
                            <Tr key={user.id}>
                                <Td>{user.name}</Td>
                                <Td>{user.surname}</Td>
                                <Td>{user.email}</Td>
                                <Td>{user.profession}</Td>
                            </Tr>
                        )}
                    </Tbody>
                </Table>
            </TableContainer>
        </div>
    )
}


export default UsersView