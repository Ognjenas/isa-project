import { Flex, FormControl, FormErrorMessage, FormLabel, Input } from "@chakra-ui/react"
interface Props {
    isValid: boolean,
    error: string,
    value: string,
    label: string
    type?: string
    onChange: (e: any) => void
}



export const TemplateErrorInput = ({ isValid, error, value, label, onChange, type }: Props) => {
    return (
        <FormControl isInvalid={!isValid}>
            <FormLabel>{label}</FormLabel>
            <Input
                h={55}
                fontSize={18}
                isRequired={true}
                value={value}
                type={type}
                onChange={onChange}
            />
            {
                !isValid &&
                <FormErrorMessage>{error}</FormErrorMessage>
            }
        </FormControl>
    )
}


export default TemplateErrorInput