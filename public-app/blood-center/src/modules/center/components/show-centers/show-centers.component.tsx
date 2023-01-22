import {
    Box,
    Button,
    Flex,
    FormControl,
    FormLabel,
    Radio,
    RadioGroup,
    Select,
    Stack,
    Table,
    TableCaption,
    TableContainer,
    Tbody,
    Td,
    Tfoot,
    Th,
    Thead,
    Tr,
} from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { NavLink, Router, useNavigate } from "react-router-dom"
import { useAuthStore } from "../../../../stores/auth-store/auth.store"
import CenterFilters from "./components/center-filters"
import { CenterDto } from "./dtos/center.dto"
import { FilterSort } from "./dtos/filter-sort.dto"
import { centerService } from "./services/center.service"
import jwt_decode from "jwt-decode"

export const ShowCentersComponent = () => {
    const [centers, setCenters] = useState<CenterDto[]>()
    const nav = useNavigate()

    const handleOnMounted = async () => {
        let res = await centerService.getCenters()
        setCenters(res)
    }

    const submit = async (filters: FilterSort) => {
        let res = await centerService.getCentersFiltered(filters)
        setCenters(res.centers)
    }

    const handleFilterChange = (values: any) => {
        const sortBy = values.sortBy
        const sort = values.sort
        const filterBy = values.filterBy
        const filterByValue = values.filterByValue

        submit({ sortBy, sort, filterBy, filterByValue })
    }

    useEffect(() => {
        handleOnMounted()
    }, [])

    return (
        <Flex
            flexDirection="column"
            height="100%"
            width="100%"
            justifyContent="flex-start"
            alignItems="center"
            gap={10}
        >
            <CenterFilters onChange={handleFilterChange} />
            <Table variant="simple" width="90%" maxWidth="90%" minWidth="90%" sx={{ tableLayout: 'fixed' }}>
                <Thead>
                    <Tr>
                        <Th textAlign={"center"} >Name</Th>
                        <Th textAlign={"center"} >Description</Th>
                        <Th textAlign={"center"} >Average grade</Th>
                        <Th textAlign={"center"} >Country</Th>
                        <Th textAlign={"center"} >City</Th>
                        <Th textAlign={"center"} >Address</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {centers?.map((center, index) => (
                        <Tr
                            cursor={'pointer'}
                            _hover={{ background: 'lightgray' }}
                            key={index}
                            onClick={() =>
                                nav(
                                    "/centers/" +
                                    center.id +
                                    "/free-appointments"
                                )
                            }
                        >
                            <Td textAlign={"center"}>{center.name}</Td>
                            <Td textAlign={"center"}>{center.description}</Td>
                            <Td textAlign={"center"}>{center.averageGrade}</Td>
                            <Td textAlign={"center"}>
                                {center.address.country}
                            </Td>
                            <Td textAlign={"center"}>{center.address.city}</Td>
                            <Td textAlign={"center"}>
                                {center.address.street +
                                    " " +
                                    center.address.number}
                            </Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>
        </Flex>
    )
}

export default ShowCentersComponent
