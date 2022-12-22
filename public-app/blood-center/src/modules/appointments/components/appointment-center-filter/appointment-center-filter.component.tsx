import { Box, Flex, Input } from "@chakra-ui/react"
import { useState } from "react"
import FilterSelect from "../../../center/components/show-centers/components/filter-select"
import { SelectOption } from "../../../center/components/show-centers/components/filter-select/filter-select.component"
import { appointmentService } from "../../services/appointment.service"

interface Props {
    onChanged: (value: Date) => void
}

export const AppointmentCenterFilterComponent = ({onChanged}:Props) => {


    const [sort, setSort] = useState("")
    const [dateTime, setDateTime] = useState(new Date());

    const handleChange = (event: any) => {
        console.log(event.target.value)
        const newDate = new Date(event.target.value)
        setDateTime(newDate);
        onChanged(newDate)      
    }

    const convertDate = (date: Date) => {
        const tzoffset = (new Date()).getTimezoneOffset() * 60000; 
        const localISOTime = (new Date(date.getTime() - tzoffset)).toISOString().slice(0, -5);
        return localISOTime
    }
    
    const sortItems: SelectOption[]= [
        {
            text: "Ascending",
            val: 'asc'
        },
        {
            text: 'Descending',
            val: 'desc'
        }
    ]

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
                 type="datetime-local"
                 value={convertDate(dateTime)}
                 onChange={handleChange}
                 onBlur={(e) => e.preventDefault()}
               />
            </Box>
            <Box>
                <FilterSelect
                    value={sort}
                    onChange={(e: any) => setSort(e.target.value)}
                    placeholder="Sort by field"
                    options={sortItems} />
            </Box>
        </Flex>
    )
}



export default AppointmentCenterFilterComponent