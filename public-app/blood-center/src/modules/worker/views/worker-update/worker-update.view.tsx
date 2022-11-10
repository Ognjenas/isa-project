import { Flex } from "@chakra-ui/react"
import UpdateWorkerForm from "../../components/update-form";
import {useParams} from "react-router-dom";



export const UpdateWorkerView = () => {

    let {id}=useParams()

    return (
        <Flex width='100%' justifyContent='center' className="update-profile-view">
            {id}
            <UpdateWorkerForm/>
        </Flex>
    )
}

export default UpdateWorkerView
