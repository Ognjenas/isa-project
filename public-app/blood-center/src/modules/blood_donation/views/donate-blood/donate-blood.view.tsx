import { Flex } from "@chakra-ui/react"
import CreateBloodDonationForm from "../../components/create-donation";



export const DonateBloodView = () => {
    return (
        <Flex width='100%' justifyContent='center' className="donate-blood-view">
            <CreateBloodDonationForm></CreateBloodDonationForm>
        </Flex>
    )
}

export default DonateBloodView