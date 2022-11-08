import { Flex } from "@chakra-ui/react"
import UpdateProfileForm from "../../components/update-form"



export const UpdateProfileView = () => {


    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view">
            <UpdateProfileForm />
        </Flex>
    )
}

export default UpdateProfileView



