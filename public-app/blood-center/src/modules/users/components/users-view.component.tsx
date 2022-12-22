import { Table, Thead, Tbody, Tr, Th, Td, TableCaption, TableContainer, Flex, Grid, GridItem, Input, FormControl, FormLabel, Button } from "@chakra-ui/react"
import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { SearchUsersDTO } from "../dtos/search-users.dto"
import { usersService } from "../services/users.service"
import TemplateForm from "../../../modules/shared/components/template-form"
import { User } from "../../profiles/model/user"
import styles from "./users-view.module.css"
import { useScroll } from "framer-motion"
import {  UserSearchMenu } from "./user-search-menu/user-filter-menu.component"
import {useAuthStore} from "../../../stores/auth-store/auth.store";
import jwt_decode from "jwt-decode";


export const UsersView = () => {


    const [users, setUsers] = useState<User[]>([])
    const navigate = useNavigate()

    const handleOnMounted = async () => {
        checkRole()
        const starterSearch: SearchUsersDTO = { name: "", surname: "" }
        const searchedUsers: User[] = await usersService.getUsers(starterSearch)
        setUsers(searchedUsers)
    }

    const checkRole = () =>
    {
        const role = useAuthStore.getState().role
        if(role!="WORKER" && role!="ADMINISTRATOR")
            navigate("/centers")
    }

    const handleSubmit = async (params: SearchUsersDTO) => {
        const searchedUsers: [User] = await usersService.getUsers(params)
        setUsers(searchedUsers)
    }


    useEffect(() => {
        handleOnMounted()
    }, [])

    return (
        <Flex direction="column" width="100%" height="100%" justifyContent={'flex-start'} alignItems={'center'} gap={10} >
            <UserSearchMenu onSubmit={handleSubmit}/>
                <Table variant='simple' width="90%" alignContent={"center"} marginTop="10px" textAlign={'center'}>
                    <Thead>
                        <Tr>
                            <Th textAlign={"center"}>Name</Th>
                            <Th textAlign={"center"}>Surname</Th>
                            <Th textAlign={"center"}>Email</Th>
                            <Th textAlign={"center"}>Profession</Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {users.map(user =>
                            <Tr key={user.id}>
                                <Td textAlign={"center"}>{user.name}</Td>
                                <Td textAlign={"center"}>{user.surname}</Td>
                                <Td textAlign={"center"}>{user.email}</Td>
                                <Td textAlign={"center"}>{user.profession}</Td>
                            </Tr>
                        )}
                    </Tbody>
                </Table>
        </Flex>
    )
}


export default UsersView