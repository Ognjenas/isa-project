import { Flex, FormControl, FormErrorMessage, FormLabel, Input } from "@chakra-ui/react"
import {toast} from "react-toastify";
import onChange = toast.onChange;

export interface SingleTime{
    id: number
    day: string
    startTime: number[]
    endTime: number[]
}

interface Props{
    time:SingleTime
    onChangeFrom: (e: any) => void
    onChangeTo: (e: any) => void
}

export const SingleTimeDisplay = ({time,onChangeFrom,onChangeTo}:Props) =>{



    return (
        <Flex direction='row' alignItems='center' justifyContent='center' width='100%' gap={5}>
            <FormControl>
                <FormLabel>{time.day} From</FormLabel>
                <Input
                    h={55}
                    fontSize={18}
                    isRequired={true}
                    value={time.startTime[0]+":"+time.startTime[1]}
                    pattern="[0-9]{1,2}[:][0-9]{1,2}"
                    onChange={onChangeFrom}
                />
            </FormControl>
            <FormControl>
                <FormLabel>{time.day} To</FormLabel>
                <Input
                    h={55}
                    fontSize={18}
                    isRequired={true}
                    value={time.endTime[0]+":"+time.endTime[1]}
                    pattern="[0-9]{1,2}[:][0-9]{1,2}"
                    onChange={onChangeTo}
                />
            </FormControl>
        </Flex>


    )


}

export default SingleTimeDisplay