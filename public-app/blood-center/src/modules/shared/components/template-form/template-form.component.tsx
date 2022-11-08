import { Box, Button, Flex, Heading } from "@chakra-ui/react"



interface Props {
    header: string
    children?: JSX.Element
    buttonText: string
    onSubmit: () => void
}

export const TemplateForm = ({ children, header, buttonText, onSubmit }: Props) => {


    return (
        <Flex direction='column' alignItems='center' justifyContent='center' gap={5} height="100%" width="100%" maxW={400}>
            <Flex justifyContent='flex-start' width='100%'>
                <Heading as='h2' fontSize={25} >{header}</Heading>
            </Flex>
            <Flex direction='column' alignItems='center' justifyContent='center' width='100%' gap={5}>
                {children}
            </Flex>
            <Flex justifyContent='flex-end' width='100%'>
                <Button onClick={() => onSubmit()} colorScheme='blue'>{buttonText}</Button>
            </Flex>
        </Flex>
    )
}


export default TemplateForm