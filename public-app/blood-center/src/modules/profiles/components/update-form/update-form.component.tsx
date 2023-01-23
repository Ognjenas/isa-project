import { Flex, FormControl, FormErrorMessage, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { setuid } from "process"
import { useEffect, useState } from "react"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateErrorRadio from "../../../shared/components/template-form/components/template-error-radio"
import TemplateForm from "../../../shared/components/template-form"
import { Sex } from "../../../shared/utils/constants"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import { FormValidator, ValidationField } from "../../../shared/utils/form.validator"
import { UpdateProfileDTO } from "../../dtos/update-profile.dto"
import { profileService } from "../../services/profile.service"
import { useNavigate } from "react-router-dom"


export const UpdateProfileForm = () => {
    const [id, setId] = useState(-1)
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
    const navigate = useNavigate()

    const handleOnMounted = async () => {

        let user = await profileService.getProfile()
        setId(user.id)
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


    const fields: ValidationField[] = [
        {
            field: 'name',
            ref: name,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'surname',
            ref: surname,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'uid',
            ref: uid,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'sex',
            ref: sex,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'profession',
            ref: profession,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'school',
            ref: school,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'country',
            ref: country,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'city',
            ref: city,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'street',
            ref: street,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'streetNumber',
            ref: streetNumber,
            validations: [FormValidator.isRequired]
        },

    ]

    let [errors, valid] = useValidator(fields)

    useEffect(() => { handleOnMounted() }, [])

    const handleSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }
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
        let ok = await profileService.updateProfile(dto, id)
        if (ok) {
            setTimeout(() => navigate("/users"), 3000)
        }
    }

    return (
        <Flex margin='auto' borderRadius={10} justifyContent='center' width='100%' className="update-profile-form" border='1px solid lightgray' w={700} p={10} marginTop={350}>
            <TemplateForm header={"Profile"} buttonText={"Save"} onSubmit={handleSubmit}>
                <>
                    <TemplateErrorInput
                        label={'Name'}
                        isValid={errors.name.isValid}
                        error={errors.name.errors[0]}
                        onChange={(e) => setName(e.target.value)}
                        value={name} />
                    <TemplateErrorInput
                        label={'Surname'}
                        isValid={errors.surname.isValid}
                        error={errors.surname.errors[0]}
                        onChange={(e) => setSurname(e.target.value)}
                        value={surname} />
                    <TemplateErrorInput
                        label={'Uid'}
                        isValid={errors.uid.isValid}
                        error={errors.uid.errors[0]}
                        onChange={(e) => setUid(e.target.value)}
                        value={uid} />
                    <TemplateErrorInput
                        label={'Profession'}
                        isValid={errors.profession.isValid}
                        error={errors.profession.errors[0]}
                        onChange={(e) => setProfession(e.target.value)}
                        value={profession} />
                    <TemplateErrorInput
                        label={'School'}
                        isValid={errors.school.isValid}
                        error={errors.school.errors[0]}
                        onChange={(e) => setSchool(e.target.value)}
                        value={school} />

                    <Grid templateColumns='repeat(2, 1fr)' templateRows='repeat(2, 1fr)' gap={5} width="100%">
                        <GridItem>
                            <TemplateErrorInput
                                label={'Country'}
                                isValid={errors.country.isValid}
                                error={errors.country.errors[0]}
                                onChange={(e) => setCountry(e.target.value)}
                                value={country} />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={'City'}
                                isValid={errors.city.isValid}
                                error={errors.city.errors[0]}
                                onChange={(e) => setCity(e.target.value)}
                                value={city} />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={'Street'}
                                isValid={errors.street.isValid}
                                error={errors.street.errors[0]}
                                onChange={(e) => setStreet(e.target.value)}
                                value={street} />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={'Street Number'}
                                isValid={errors.streetNumber.isValid}
                                error={errors.streetNumber.errors[0]}
                                onChange={(e) => setStreetNumber(e.target.value)}
                                value={streetNumber} />
                        </GridItem>
                    </Grid>
                    <TemplateErrorRadio
                        label={'Sex'}
                        isValid={errors.sex.isValid}
                        error={errors.sex.errors[0]}
                        value={sex}
                        onChange={(val: any) => setSex(val)}
                        values={
                            [
                                { text: 'Male', value: Sex.MALE },
                                { text: 'Female', value: Sex.FEMALE }
                            ]
                        }
                    />

                </>
            </TemplateForm>
        </Flex>
    )
}


export default UpdateProfileForm