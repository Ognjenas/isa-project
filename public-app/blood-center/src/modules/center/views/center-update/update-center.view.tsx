import { Flex } from "@chakra-ui/react"
import UpdateCenterForm from "../../components/update-form"



export const UpdateCenterView = () => {


    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view">
            <UpdateCenterForm />
        </Flex>
    )
}

export default UpdateCenterView