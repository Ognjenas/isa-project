import { Flex, FormControl, FormHelperText, FormLabel, Input, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { useState } from "react"
import TemplateForm from "../../../shared/components/template-form"
import { RegistrationDTO } from "../../dtos/registration.dto"
import { authService } from "../../services/auth.service"

enum Sex { MALE="MALE", FEMALE="FEMALE" }

export const RegistrationForm = () => {

    const [email, setEmail] = useState("stjepanovicsrdjan2000@gmail.com")
    const [name, setName] = useState("Srdjan")
    const [surname, setSurname] = useState("Stjepanovic")
    const [password, setPassword] = useState("Stjepanovic")
    const [sex, setSex] = useState(Sex.MALE)
    const [uid, setUid] = useState("Stjepanovic")
    const [profession, setProfession] = useState("Stjepanovic")
    const [school, setSchool] = useState("Stjepanovic")
    const [city, setCity] = useState("Stjepanovic")
    const [number, setNumber] = useState("Stjepanovic")
    const [country, setCountry] = useState("Stjepanovic")
    const [street, setStreet] = useState("Stjepanovic")

    const [isEmailAddressValid, setIsEmailAddressValid] = useState(true)

    const handleSubmit = async () => {
        const dto: RegistrationDTO = {
            name,
            surname,
            sex,
            profession,
            school,
            uid,
            address: {
                country,
                city,
                street,
                number
            }
        }
        await authService.registrate(dto)
    }


    return (
        <Flex margin='auto' justifyContent='center' width='100%'>
            <TemplateForm header={"Registration"} buttonText={"Save"} onSubmit={handleSubmit}>
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
                    <FormControl >
                        <FormLabel>Password</FormLabel>
                        <Input
                            isRequired={true}
                            value={password}
                            type='password'
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Sex</FormLabel>
                        <RadioGroup value={sex} onChange={(val:any) => setSex(val)}>
                            <Stack direction='row' justifyContent='flex-start' gap={10}>
                                <Radio value={Sex.MALE}>Male</Radio>
                                <Radio value={Sex.FEMALE}>Female</Radio>
                            </Stack>
                        </RadioGroup>
                    </FormControl>
                    <FormControl >
                        <FormLabel>City</FormLabel>
                        <Input
                            isRequired={true}
                            value={city}
                            onChange={(e) => setCity(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Country</FormLabel>
                        <Input
                            isRequired={true}
                            value={country}
                            onChange={(e) => setCountry(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Street</FormLabel>
                        <Input
                            isRequired={true}
                            value={street}
                            onChange={(e) => setStreet(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Number</FormLabel>
                        <Input
                            isRequired={true}
                            value={number}
                            onChange={(e) => setNumber(e.target.value)}
                        />
                    </FormControl>
                </>
            </TemplateForm>

        </Flex>
    )
}


export default RegistrationForm