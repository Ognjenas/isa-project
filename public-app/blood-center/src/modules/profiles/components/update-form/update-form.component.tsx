import { Flex, FormControl, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { setuid } from "process"
import { useEffect, useState } from "react"
import TemplateForm from "../../../shared/components/template-form"
import { Sex } from "../../../shared/utils/constants"
import { UpdateProfileDTO } from "../../dtos/update-profile.dto"
import { profileService } from "../../services/profile.service"


export const UpdateProfileForm = () => {

    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [sex, setSex] = useState<Sex>(Sex.MALE)
    const [uid, setUid] = useState("")
    const [profession, setProfession] = useState("")
    const [school, setSchool] = useState("")
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")
    const [street, setStreet] = useState("")
    const [streetNumber, setStreetNumber] = useState("")
    const [addressId, setAddressId] = useState(0)

    const handleOnMounted = async () => {
        let user = await profileService.getProfile()
        setName(user.name)
        setSurname(user.surname)
        setSex(user.sex)
        setUid(user.uid)
        setProfession(user.profession)
        setSchool(user.school)
        setCountry(user.address.country)
        setCity(user.address.city)
        setStreet(user.address.street)
        setStreetNumber(user.address.number)
        setAddressId(user.address.id)
    }

    useEffect(() => { handleOnMounted() }, [])

    const handleSubmit = async () => {
        const dto: UpdateProfileDTO = {
            name,
            surname,
            sex,
            profession,
            school,
            uid,
            address: {
                id: addressId,
                country,
                city,
                street,
                number: streetNumber
            }
        }
        await profileService.updateProfile(dto)
    }

    return (
        <Flex margin='auto' justifyContent='center' width='100%' className="update-profile-form" border='1px solid lightgray' w={600} p={20}>
            <TemplateForm header={"Update Profile"} buttonText={"Save"} onSubmit={handleSubmit}>
                <>
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
                        <FormLabel>Uid</FormLabel>
                        <Input
                            isRequired={true}
                            value={uid}
                            onChange={(e) => setUid(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>Profession</FormLabel>
                        <Input
                            isRequired={true}
                            value={profession}
                            onChange={(e) => setProfession(e.target.value)}
                        />
                    </FormControl>
                    <FormControl >
                        <FormLabel>School</FormLabel>
                        <Input
                            isRequired={true}
                            value={school}
                            onChange={(e) => setSchool(e.target.value)}
                        />
                    </FormControl>
                    <Grid templateColumns='repeat(2, 1fr)' templateRows='repeat(2, 1fr)' gap={5} width="100%">
                        <GridItem>
                            <FormControl >
                                <FormLabel>Country</FormLabel>
                                <Input
                                    isRequired={true}
                                    value={country}
                                    onChange={(e) => setCountry(e.target.value)}
                                />
                            </FormControl>
                        </GridItem>
                        <GridItem>
                            <FormControl >
                                <FormLabel>City</FormLabel>
                                <Input
                                    isRequired={true}
                                    value={city}
                                    onChange={(e) => setCity(e.target.value)}
                                />
                            </FormControl>
                        </GridItem>
                        <GridItem>
                            <FormControl >
                                <FormLabel>Street</FormLabel>
                                <Input
                                    isRequired={true}
                                    value={street}
                                    onChange={(e) => setStreet(e.target.value)}
                                />
                            </FormControl>
                        </GridItem>
                        <GridItem>
                            <FormControl >
                                <FormLabel>Street Number</FormLabel>
                                <Input
                                    isRequired={true}
                                    value={streetNumber}
                                    onChange={(e) => setStreetNumber(e.target.value)}
                                />
                            </FormControl>
                        </GridItem>
                    </Grid>
                    <FormControl >
                        <FormLabel>Sex</FormLabel>
                        <RadioGroup value={sex} onChange={(val: any) => setSex(val)}>
                            <Stack direction='row' justifyContent='flex-start' gap={10}>
                                <Radio value={Sex.MALE}>Male</Radio>
                                <Radio value={Sex.FEMALE}>Female</Radio>
                            </Stack>
                        </RadioGroup>
                    </FormControl>
                </>
            </TemplateForm>
        </Flex>
    )
}


export default UpdateProfileForm