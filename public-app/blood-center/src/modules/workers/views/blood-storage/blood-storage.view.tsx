import { Flex } from "@chakra-ui/react"
import BloodStorageComponent from "../../components/blood-storage/blood-storage.component";



export const BloodStorageView = () => {

    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view">
            <BloodStorageComponent/>
        </Flex>
    )
}

export default BloodStorageView
