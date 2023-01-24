import {Flex, FormControl, FormErrorMessage, FormLabel, Input, Select} from "@chakra-ui/react"
import React from "react";
interface Props {
    elements:SelectElementTemp[]
    label:string
    onSelect:(value:string) => void
    isValid: boolean,
    error: string
}

export interface SelectElementTemp{
    value:string
    label:string
}

export const TemplateErrorSelect = ({isValid,label,elements,onSelect,error}: Props) => {
    return (
        <FormControl isInvalid={!isValid}>
            <FormLabel>{label}</FormLabel>
            <Select onChange={(e:any) => onSelect(e.target.value) }>
                {
                    elements.map((element)=>{
                        return(
                            <option value={element.value}>{element.label}</option>
                        )
                    })
                }
            </Select>
            {
                !isValid &&
                <FormErrorMessage>{error}</FormErrorMessage>
            }
        </FormControl>
    )
}


export default TemplateErrorSelect