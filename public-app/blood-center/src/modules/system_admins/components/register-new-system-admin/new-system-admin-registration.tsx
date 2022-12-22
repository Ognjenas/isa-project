import { Flex, FormControl, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Stack} from "@chakra-ui/react"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify"
import TemplateForm from "../../../shared/components/template-form"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateErrorRadio from "../../../shared/components/template-form/components/template-error-radio"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import {FormValidator, ValidationField} from "../../../shared/utils/form.validator"
import { RegistrationDTO } from "../../../auth/dtos/registration.dto"
import { adminService } from "../../services/admin.service"
import { AdminRegistrationDTO } from "../../dtos/admin-registration.dto"

enum Sex {
    MALE = "MALE",
    FEMALE = "FEMALE",
}

export const AdminRegistrationForm = () => {
    const [email, setEmail] = useState("")
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [password, setPassword] = useState("")
    const [sex, setSex] = useState(Sex.MALE)
    const [uid, setUid] = useState("")
    const [profession, setProfession] = useState("")
    const [school, setSchool] = useState("")
    const [city, setCity] = useState("")
    const [country, setCountry] = useState("")
    const [number, setNumber] = useState("")
    const [street, setStreet] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async () => {
        const dto: AdminRegistrationDTO = {
            user:{
                email,
                password,
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
                    number,
                },
            }
        }

        let ok = await adminService.registrate(dto)
        if (ok) {
            setTimeout(() => navigate("/profile"), 3000)
        }
    } 
    

    const fields: ValidationField[] = [
        {
            field: "name",
            ref: name,
            validations: [FormValidator.isRequired],
        },
        {
            field: "email",
            ref: email,
            validations: [FormValidator.isEmail, FormValidator.isRequired],
        },
        {
            field: "surname",
            ref: surname,
            validations: [FormValidator.isRequired],
        },
        {
            field: "password",
            ref: password,
            validations: [
                FormValidator.isRequired,
                FormValidator.minLength(8),
                FormValidator.maxLength(20),
            ],
        },
        {
            field: "uid",
            ref: uid,
            validations: [FormValidator.isRequired],
        },
        {
            field: "sex",
            ref: sex,
            validations: [FormValidator.isRequired],
        },
        {
            field: "profession",
            ref: profession,
            validations: [FormValidator.isRequired],
        },
        {
            field: "school",
            ref: school,
            validations: [FormValidator.isRequired],
        },
        {
            field: "country",
            ref: country,
            validations: [FormValidator.isRequired],
        },
        {
            field: "city",
            ref: city,
            validations: [FormValidator.isRequired],
        },
        {
            field: "street",
            ref: street,
            validations: [FormValidator.isRequired],
        },
        {
            field: "number",
            ref: number,
            validations: [FormValidator.isRequired],
        },
    ]

    let [errors, valid] = useValidator(fields)

    return (
        <Flex
            margin="auto"
            justifyContent="center"
            width="100%"
            className="update-profile-form"
            border="1px solid lightgray"
            w={600}
            p={20}
        >
            <TemplateForm
                header={"Registration"}
                buttonText={"Save"}
                onSubmit={handleSubmit}
            >
                <>
                    <TemplateErrorInput
                        label={"Email"}
                        isValid={errors.email.isValid}
                        error={errors.email.errors[0]}
                        onChange={(e) => setEmail(e.target.value)}
                        value={email}
                    />
                    <TemplateErrorInput
                        label={"Password"}
                        isValid={errors.password.isValid}
                        error={errors.password.errors[0]}
                        onChange={(e) => setPassword(e.target.value)}
                        value={password}
                    />
                    <TemplateErrorInput
                        label={"Name"}
                        isValid={errors.name.isValid}
                        error={errors.name.errors[0]}
                        onChange={(e) => setName(e.target.value)}
                        value={name}
                    />
                    <TemplateErrorInput
                        label={"Surname"}
                        isValid={errors.surname.isValid}
                        error={errors.surname.errors[0]}
                        onChange={(e) => setSurname(e.target.value)}
                        value={surname}
                    />
                    <TemplateErrorInput
                        label={"Uid"}
                        isValid={errors.uid.isValid}
                        error={errors.uid.errors[0]}
                        onChange={(e) => setUid(e.target.value)}
                        value={uid}
                    />
                    <TemplateErrorInput
                        label={"Profession"}
                        isValid={errors.profession.isValid}
                        error={errors.profession.errors[0]}
                        onChange={(e) => setProfession(e.target.value)}
                        value={profession}
                    />
                    <TemplateErrorInput
                        label={"School"}
                        isValid={errors.school.isValid}
                        error={errors.school.errors[0]}
                        onChange={(e) => setSchool(e.target.value)}
                        value={school}
                    />

                    <Grid
                        templateColumns="repeat(2, 1fr)"
                        templateRows="repeat(2, 1fr)"
                        gap={5}
                        width="100%"
                    >
                        <GridItem>
                            <TemplateErrorInput
                                label={"Country"}
                                isValid={errors.country.isValid}
                                error={errors.country.errors[0]}
                                onChange={(e) => setCountry(e.target.value)}
                                value={country}
                            />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={"City"}
                                isValid={errors.city.isValid}
                                error={errors.city.errors[0]}
                                onChange={(e) => setCity(e.target.value)}
                                value={city}
                            />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={"Street"}
                                isValid={errors.street.isValid}
                                error={errors.street.errors[0]}
                                onChange={(e) => setStreet(e.target.value)}
                                value={street}
                            />
                        </GridItem>
                        <GridItem>
                            <TemplateErrorInput
                                label={"Street Number"}
                                isValid={errors.number.isValid}
                                error={errors.number.errors[0]}
                                onChange={(e) => setNumber(e.target.value)}
                                value={number}
                            />
                        </GridItem>
                    </Grid>
                    <TemplateErrorRadio
                        label={"Sex"}
                        isValid={errors.sex.isValid}
                        error={errors.sex.errors[0]}
                        value={sex}
                        onChange={(val: any) => setSex(val)}
                        values={[
                            { text: "Male", value: Sex.MALE },
                            { text: "Female", value: Sex.FEMALE },
                        ]}
                    />
                </>
            </TemplateForm>
        </Flex>
    )
}

export default AdminRegistrationForm