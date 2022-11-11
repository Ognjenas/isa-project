import { Box, Button, Flex, FormControl, FormLabel, Radio, RadioGroup, Select, Stack, Table, TableCaption, TableContainer, Tbody, Td, Tfoot, Th, Thead, Tr } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { useAuthStore } from "../../../../stores/auth-store/auth.store"
import CenterFilters from "./components/center-filters";
import { CenterDto } from "./dtos/center.dto";
import { centerService } from "./services/cetner.service";



export const ShowCentersComponent = () => {

    const [centers, setCenters] = useState<CenterDto[]>();

    const handleOnMounted = async () => {
        let res = await centerService.getCenters();
        setCenters(res);
    }

    const submit = async (field: string, sort: string) => {
        let res = await centerService.getCentersSorted(field, sort);
        setCenters(res);
    }

    const handleFilterChange = (values: any) => {

        const field = values.sortBy
        const sort = values.sort
        if (field.trim() != "" && sort.trim() != "")
            submit(field, sort)
    }

    useEffect(() => { handleOnMounted() }, [])


    return (
        <Flex justifyContent="center" flexDirection='column' height="100%" width="100%">
            <CenterFilters onChange={handleFilterChange} />
            <Table variant='simple' margin="auto" width='70%' >
                <TableCaption>Blood centers</TableCaption>
                <Thead>
                    <Tr>
                        <Th>Name</Th>
                        <Th>Description</Th>
                        <Th>Average grade</Th>
                        <Th>Country</Th>
                        <Th>City</Th>
                        <Th>Address</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {
                        centers?.map((center, index) => (
                            <Tr key={index}>
                                <Td>{center.name}</Td>
                                <Td>{center.description}</Td>
                                <Td>{center.averageGrade}</Td>
                                <Td>{center.address.country}</Td>
                                <Td>{center.address.city}</Td>
                                <Td>{center.address.street + ' ' + center.address.streetNumber}</Td>
                            </Tr>
                        ))
                    }
                </Tbody>
            </Table>
        </Flex>
    )
}

export default ShowCentersComponent