import {Flex} from "@chakra-ui/react";
import UpdatePasswordForm from "../../components/update-password-form";

export const UpdatePasswordView = () => {


    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view" marginTop={20} marginBottom={20}>
            <UpdatePasswordForm/>
        </Flex>
    )
}

export default UpdatePasswordView