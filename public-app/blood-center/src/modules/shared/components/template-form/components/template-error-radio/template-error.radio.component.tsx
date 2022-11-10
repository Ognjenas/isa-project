import { FormControl, FormLabel, RadioGroup, Stack, Radio, FormErrorMessage } from "@chakra-ui/react"
import { Sex } from "../../../../utils/constants"

interface Props {
    isValid: boolean,
    error: string,
    value: string,
    label: string,
    values: any[],
    onChange: (e: any) => void
}


export const TemplateErrorRadio = ({ isValid, error, value, label, values, onChange }: Props) => {


    return (
        <FormControl isInvalid={!isValid}>
            <FormLabel>{label}</FormLabel>
            <RadioGroup value={value} onChange={onChange}>
                <Stack direction='row' justifyContent='flex-start' gap={10}>
                    {
                        values.map(val => (
                            <Radio key={val.value} value={val.value}>{val.text}</Radio>
                        ))
                    }
                </Stack>
            </RadioGroup>
            {
                !isValid &&
                <FormErrorMessage>{error}</FormErrorMessage>
            }
        </FormControl>
    )
}


export default TemplateErrorRadio