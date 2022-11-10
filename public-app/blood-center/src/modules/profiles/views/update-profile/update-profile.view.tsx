import { Flex } from "@chakra-ui/react"
import UpdateProfileForm from "../../components/update-form"



export const UpdateProfileView = () => {


    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view" marginTop={20} marginBottom={20}>
            <UpdateProfileForm />
        </Flex>
    )
}

export default UpdateProfileView



