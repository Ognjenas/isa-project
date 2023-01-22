import { Button, Flex, Table, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react"
import moment from "moment"
import { useState } from "react"
import { CenterDto } from "../../center/components/show-centers/dtos/center.dto"
import AppointmentCenterFilterComponent from "../components/appointment-center-filter"
import { appointmentService } from "../services/appointment.service"

export interface ShowAppointmentDTO {
    id: number,
    startTime: Date,
    duration: number,
    center: CenterDto
}

export const ShowAppointmentsView = () => {


    const [appointments, setAppointments] = useState<ShowAppointmentDTO[]>([]);
    const [sort, setSort] = useState("")
    const [startTime, setStartTime] = useState<Date>(new Date());

    const updateAppointments = async (value: Date) =>{
        setStartTime(value)
        const data = await appointmentService.getFreeAppointmentsForDateTime(value);
        setAppointments(data)
    }

    const updateSort = async (value: string) => {
        setSort(value)
        const data = await appointmentService.getFreeAppointmentsForDateTime(startTime, value)
        setAppointments(data)
    }

    const loadData = async () => {
        const data = await appointmentService.getFreeAppointmentsForDateTime(startTime, sort)
        setAppointments(data)
    }

    const convertDate = (date: Date) => {
        if(!date) return ""
        const dt = new Date(date)
        const tzoffset = (new Date()).getTimezoneOffset() * 60000; 
        const localISOTime = moment(new Date(dt.getTime() - tzoffset)).format("YYYY-MM-DD HH:mm")
        return localISOTime
    }

    
    const reserveAppointment = async (appointmentId: number) => {
        const res = await appointmentService.reserveAppointment(
            appointmentId,
            1
        )
        if (res == true) {
            loadData()
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
        <AppointmentCenterFilterComponent onSortChanged={updateSort} onChanged={updateAppointments}/>
        <Table variant="simple" width="90%">
            <Thead>
                <Tr>
                    <Th textAlign={"center"}>Name</Th>
                    <Th textAlign={"center"}>Description</Th>
                    <Th textAlign={"center"}>Average grade</Th>
                    <Th textAlign={"center"}>Country</Th>
                    <Th textAlign={"center"}>City</Th>
                    <Th textAlign={"center"}>Address</Th>
                    <Th textAlign={"center"}>Start Time</Th>
                    <Th textAlign={"center"}>Duration(mins)</Th>
                </Tr>
            </Thead>
            <Tbody>
                {appointments?.map(({startTime, duration, center, id}, index) => (
                    <Tr key={index}>
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
                        <Td textAlign={"center"}>{convertDate(startTime)}</Td>
                        <Td textAlign={"center"}>{duration}</Td>
                        <Td textAlign={"center"}>
                                <Button
                                    colorScheme="teal"
                                    variant="ghost"
                                    onClick={() => reserveAppointment(id)}
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



export default ShowAppointmentsView