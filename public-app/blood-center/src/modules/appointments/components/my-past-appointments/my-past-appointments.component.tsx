import {
    Box,
    Button,
    Flex,
    Input,
    Table,
    Tbody,
    Td,
    Th,
    Thead,
    Tr,
} from "@chakra-ui/react"
import moment from "moment"
import { useEffect, useState } from "react"
import { appointmentService } from "../../services/appointment.service"
import { ShowAppointmentDTO } from "../../views/show-appointments.view"

export const MyPastAppointments = () => {
    const [appointments, setAppointments] = useState<ShowAppointmentDTO[]>([])
    const [sort, setSort] = useState('asc');



    const handleOnMounted = async (sort: string) => {
        let res = await appointmentService.getMyPastAppointments(sort)
        setAppointments(res)
    }
    useEffect(() => {
        handleOnMounted(sort)
    }, [sort])

    const convertDate = (date: Date) => {
        if (!date) return ""
        const dt = new Date(date)
        const tzoffset = new Date().getTimezoneOffset() * 60000
        const localISOTime = moment(new Date(dt.getTime() - tzoffset)).format(
            "YYYY-MM-DD HH:mm"
        )
        return localISOTime
    }
    const changeSort = async () => {
        sort === 'asc' ? setSort('desc') : setSort('asc');
    }

    return (
        <Flex
            flexDirection="column"
            height="100%"
            width="100%"
            justifyContent="flex-start"
            alignItems="center"
            gap={10}
        >
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
            ></Flex>
            <Table variant="simple" width="90%">
                <Thead>
                    <Tr>
                        <Th textAlign={"center"}>Center name</Th>
                        <Th textAlign={"center"} onClick={() => changeSort()}>Start Time</Th>
                        <Th textAlign={"center"}>Duration</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {appointments.map((a) => (
                        <Tr key={a.id}>
                            <Td textAlign={"center"}>{a.center.name}</Td>
                            <Td textAlign={"center"}>
                                {convertDate(a.startTime)}
                            </Td>
                            <Td textAlign={"center"}>{a.duration}</Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>
        </Flex>
    )
}

export default MyPastAppointments
function changeSort() {
    throw new Error("Function not implemented.")
}

