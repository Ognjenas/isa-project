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
import FilterSelect from "../../../center/components/show-centers/components/filter-select"
import { SelectOption } from "../../../center/components/show-centers/components/filter-select/filter-select.component"
import { appointmentService } from "../../services/appointment.service"
import { useParams } from "react-router-dom"

export interface FreeAppointmentDTO {
    id: number
    startTime: Date
    endTime: Date
    centerName: string
}

export interface FreeAppointmentsParams {
    sortby: string
}

export const FreeAppointments = () => {
    const { cid } = useParams()
    const [sortType, setSortType] = useState("asc")
    const [id, setId] = useState(cid ? +cid : -1)
    const [appointments, setAppointments] = useState<FreeAppointmentDTO[]>([])
    const handleOnMounted = async (sort: string) => {
        let res = await appointmentService.getFreeAppointments(id, sort)
        setAppointments(res)
    }
    useEffect(() => {
        handleOnMounted(sortType)
        console.log(appointments)
    }, [sortType])

    const convertDate = (date: Date) => {
        if (!date) return ""
        const dt = new Date(date)
        const tzoffset = new Date().getTimezoneOffset() * 60000
        const localISOTime = moment(new Date(dt.getTime() - tzoffset)).format(
            "YYYY-MM-DD HH:mm"
        )
        return localISOTime
    }

    const reserveAppointment = async (appointmentId: number) => {
        const res = await appointmentService.reserveAppointment(
            appointmentId,
            1
        )
        if (res == true) {
            handleOnMounted("asc")
        }
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
            <Table variant="simple" width="90%">
                <Thead>
                    <Tr>
                        <Th textAlign={"center"}>Center name</Th>
                        <Th
                            textAlign={"center"}
                            onClick={() => {
                                setSortType(sortType == "asc" ? "desc" : "asc")
                            }}
                        >
                            Start Time
                        </Th>
                        <Th textAlign={"center"}>End Time</Th>
                        <Th textAlign={"center"}></Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {appointments.map((a) => (
                        <Tr key={a.id}>
                            <Td textAlign={"center"}>{a.centerName}</Td>
                            <Td textAlign={"center"}>
                                {convertDate(a.startTime)}
                            </Td>
                            <Td textAlign={"center"}>
                                {convertDate(a.endTime)}
                            </Td>
                            <Td textAlign={"center"}>
                                <Button
                                    colorScheme="teal"
                                    variant="ghost"
                                    onClick={() => reserveAppointment(a.id)}
                                >
                                    Reserve
                                </Button>
                            </Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>
        </Flex>
    )
}

export default FreeAppointments
