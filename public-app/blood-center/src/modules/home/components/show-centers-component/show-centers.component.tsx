import { Box, Button, Flex, FormControl, FormLabel, Radio, RadioGroup, Select, Stack, Table, TableCaption, TableContainer, Tbody, Td, Tfoot, Th, Thead, Tr } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { useAuthStore } from "../../../../stores/auth-store/auth.store"
import { CenterDto } from "./dtos/center.dto";
import { centerService } from "./services/cetner.service";



export const ShowCentersComponent = () => {

    const [centers, setCenters] = useState<CenterDto[]>();
    const [field, setField] = useState('');
    const [sort, setSort] = useState('');

    const handleOnMounted = async () => {
        let res = await centerService.getCenters();
        setCenters(res);
    }

    const onSubmit = async () => {
        let res = await centerService.getCentersSorted(field, sort);
        setCenters(res);
        console.log(sort);
        console.log(field);
    }

    useEffect(() => { handleOnMounted() }, [])


    return (
        <Flex justifyContent="center" flexDirection='column'>
                <Table variant='simple' margin="auto" width='70%'>
                    <TableCaption>Blood centers</TableCaption>
                    <Thead>
                    <Tr>
                        <Th>Name</Th>
                        <Th>Description</Th>
                        <Th>Average grade</Th>
                        <Th>Country</Th>
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
                                    <Td>{center.address.street + ' ' +center.address.number}</Td>
                                </Tr>
                            ))
                        }
                    </Tbody>
                </Table>
            <Flex flexDirection='row' justifyContent='center'>
                    <FormControl >
                    <FormLabel>Field</FormLabel>
                        <Select placeholder='Select field' 
                        value={field} onChange={(e) => setField(e.target.value)}>
                            <option value='name'>Name</option>
                            <option value='description'>Description</option>
                            <option value='averageGrade'>Average grade</option>
                            <option value='country'>Country</option>
                            <option value='street'>Address</option>
                        </Select>
                    </FormControl>
                    <FormLabel>By</FormLabel>
                    <RadioGroup onChange={setSort} value={sort}>
                        <Stack >
                        <Radio value='asc'>Ascending</Radio>
                        <Radio value='desc'>Descending</Radio>
                        </Stack>
                    </RadioGroup>    
                <Button size='lg' onClick={() => onSubmit()} colorScheme='blue'>Sort</Button>
            </Flex>
        </Flex>
    )
}

export default ShowCentersComponent