import { Flex, FormControl, FormHelperText, FormLabel, Input } from "@chakra-ui/react"
import { useState } from "react"
import TemplateForm from "../../../shared/components/template-form"


export const UpdateProfileForm = () => {

    const [email, setEmail] = useState("stjepanovicsrdjan2000@gmail.com")
    const [name, setName] = useState("Srdjan")
    const [surname, setSurname] = useState("Stjepanovic")

    const [isEmailAddressValid, setIsEmailAddressValid] = useState(true)

    const handleSubmit = () => {
        console.log(email, name, surname)
    }

    return (
        <Flex margin='auto' justifyContent='center' width='100%' className="update-profile-form">
            <TemplateForm header={"Update Profile"} buttonText={"Save"} onSubmit={handleSubmit}>
                <>
                    <FormControl >
                        <FormLabel>Email address</FormLabel>
                        <Input
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Name</FormLabel>
                        <Input
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Surname</FormLabel>
                        <Input
                            isRequired={true}
                            value={surname}
                            onChange={(e) => setSurname(e.target.value)}
                        />
                    </FormControl>
                </>
            </TemplateForm>

        </Flex>
    )
}


export default UpdateProfileForm