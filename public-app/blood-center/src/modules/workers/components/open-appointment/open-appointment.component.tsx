import { Box, Button, Flex, Input } from "@chakra-ui/react"
import { useState } from "react"
import FilterSelect from "../../../center/components/show-centers/components/filter-select"
import { SelectOption } from "../../../center/components/show-centers/components/filter-select/filter-select.component"
import { useNavigate } from "react-router-dom"
import { appointmentService } from "../../../appointments/services/appointment.service"

export const OpenAppointmentComponent = () => {

    const [appointmentId, setAppointmentId] = useState<number>();
    const navigate = useNavigate();

    const handleChange = (e: any) => {
        setAppointmentId(e.target.value)
    }

    const handleSubmit = async (e: any) => {
        const statement = await appointmentService.checkAppointment(appointmentId);
        if (statement) {
            navigate("/blood-donation/" + appointmentId)
        }
    }

    return (
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
            gap={15}>

            <Box>
                <Input
                    color={"white"}
                    type="number"
                    value={appointmentId}
                    onChange={handleChange}
                    onBlur={(e) => e.preventDefault()}
                />
            </Box>
            <Box>
                <Button
                    border={1}
                    borderColor={'white'}
                    colorScheme="teal"
                    variant="ghost"
                    onClick={handleSubmit}
                >
                    Open appointment
                </Button>
            </Box>
        </Flex>
    )
}

export default OpenAppointmentComponent