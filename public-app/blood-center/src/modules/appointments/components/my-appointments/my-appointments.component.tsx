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
import { useParams } from "react-router-dom"
import { FreeAppointmentDTO } from "../free-appointments/free-appointment.component"

export const MyAppointments = () => {
    const [appointments, setAppointments] = useState<FreeAppointmentDTO[]>([])
    const handleOnMounted = async () => {
        let res = await appointmentService.getMyAppointments()
        setAppointments(res.appointments)
    }
    useEffect(() => {
        handleOnMounted()
    })

    const convertDate = (date: Date) => {
        if (!date) return ""
        const dt = new Date(date)
        const tzoffset = new Date().getTimezoneOffset() * 60000
        const localISOTime = moment(new Date(dt.getTime() - tzoffset)).format(
            "YYYY-MM-DD HH:mm"
        )
        return localISOTime
    }

    const cancelAppointment = async (appointmentId: number) => {
        await appointmentService.cancelAppointment(appointmentId)
        handleOnMounted()
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
                        <Th textAlign={"center"}>Start Time</Th>
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
                                    onClick={() => cancelAppointment(a.id)}
                                >
                                    Cancel
                                </Button>
                            </Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>
        </Flex>
    )
}

export default MyAppointments
