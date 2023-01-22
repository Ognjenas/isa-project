import { Flex } from "@chakra-ui/react"


interface Props {
    text: string
}

export const SideMenuTooltip = ({text}: Props) => {

    return (
        <Flex className="tooltip" position={'absolute'} top={'50%'} right={'-250%'} background={'black'} color={"white"} padding="10px">
            {text}
        </Flex>
    )
}