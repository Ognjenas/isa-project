import { Box, Flex, Select } from "@chakra-ui/react"


export interface SelectOption {
    val: any,
    text: string,
}



interface Props {
    options: SelectOption[],
    value: any,
    placeholder?: string,
    onChange: (e: any) => void,

}

export const FilterSelect = ({ placeholder, value, onChange, options }: Props) => {

    return (
        <Flex direction="column" alignItems="flex-start" border="1px solid white" padding="2px" borderRadius="3px" height="100%">
            <Select
                focusBorderColor="none"
                outline="none"
                border={'none'}
                width="200px"
                placeholder={placeholder}
                borderRadius={3}
                cursor="pointer"
                color="white"
                value={value} onChange={onChange}>
                {
                    options.map(opt => (
                        <option style={{ color: "black" }} key={opt.val} value={opt.val}>{opt.text}</option>
                    ))
                }
            </Select>
        </Flex>

    )
}
export default FilterSelect