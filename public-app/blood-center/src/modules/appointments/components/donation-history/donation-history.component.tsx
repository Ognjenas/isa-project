import { Flex, Table, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { appointmentService } from "../../services/appointment.service"

export interface Appointment {
    id: number,
    startTime: Date,
    endTime: Date,
    duration: number,
    user: any,
    center: any
}

export const DonationHistory = () => {

    const { userId } = useParams()
    const [appointments, setAppointments] = useState<Appointment[]>([]);

    const handleOnMounted = async () => {
        const id = userId ?? "0"
        const apps = await appointmentService.getDonationHistory(+id);
        setAppointments(apps)
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

            </Flex>
            <Table variant="simple" width="90%" maxWidth="90%" minWidth="90%" sx={{ tableLayout: 'fixed' }}>
                <Thead>
                    <Tr>
                        <Th textAlign={"center"} >Id</Th>
                        <Th textAlign={"center"} >Start Time</Th>
                        <Th textAlign={"center"} >Duration(mins)</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {appointments?.map((app, index) => (
                        <Tr
                            cursor={'pointer'}
                            _hover={{ background: 'lightgray' }}
                            key={index}
                        >
                            <Td textAlign={"center"}>{app.id}</Td>
                            <Td textAlign={"center"}>{new Date(app.startTime).toDateString()}</Td>
                            <Td textAlign={"center"}>{app.duration}</Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>
        </Flex>
    )
}