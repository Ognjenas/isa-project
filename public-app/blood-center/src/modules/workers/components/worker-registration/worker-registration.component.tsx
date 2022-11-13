import { Flex, FormControl, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Select, Stack} from "@chakra-ui/react"
import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify"
import TemplateForm from "../../../shared/components/template-form"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateErrorRadio from "../../../shared/components/template-form/components/template-error-radio"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import {FormValidator, ValidationField} from "../../../shared/utils/form.validator"
import { WorkerRegistrationDTO } from "../../dtos/worker-registration.dto"
import { Center } from "../../model/center"
import { workerService } from "../../services/worker.service"

enum Sex {
    MALE = "MALE",
    FEMALE = "FEMALE",
}

export const WorkerRegistrationForm = () => {

    const [email, setEmail] = useState("")
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")
    const [sex, setSex] = useState(Sex.MALE)
    const [uid, setUid] = useState("")
    const [profession, setProfession] = useState("")
    const [school, setSchool] = useState("")
    const [city, setCity] = useState("")
    const [country, setCountry] = useState("")
    const [number, setNumber] = useState("")
    const [street, setStreet] = useState("")
    const [center, setCenter] = useState(-1)
    const [centers, setCenters] = useState<Center[]>([])
    const navigate = useNavigate()

    const handleSubmit = async () => {
        if (password === confirmPassword) {
            const dto: WorkerRegistrationDTO = {
                user:{
                    email: email,
                    password: password,
                    name: name,
                    surname: surname,
                    sex: sex,
                    profession: profession,
                    school: school,
                    uid: uid,
                    address: {
                        country: country,
                        city: city,
                        street: street,
                        number: number,
                    },
                },
                centerId: center
            }

            let ok = await workerService.registrate(dto)
            if (ok) {
                setTimeout(() => navigate("/users"), 3000)
            }
        } else {
            toast.error("Passwords must be same")
        }
    }

    const handleOnMounted = async () => {
        let centers: Center[] = await workerService.getCenters()
        if(centers.length > 0) {
            setCenter(centers[0].id)
        }
        setCenters(centers)
    }

    useEffect(() => {
        handleOnMounted()
    }, [])

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
            field: "confirmPassword",
            ref: confirmPassword,
            validations: [
                FormValidator.isRequired,
                FormValidator.isEqualToField(password, "password"),
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
        {
            field: "center",
            ref: number,
            validations: [FormValidator.isCenterSelected],
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
                        type={"password"}
                        value={password}
                    />
                    <TemplateErrorInput
                        label={"Repeat password"}
                        isValid={errors.confirmPassword.isValid}
                        error={errors.confirmPassword.errors[0]}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        type={"password"}
                        value={confirmPassword}
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
                        <GridItem>
                            <label>Select center</label>
                            <Select name="Center" defaultValue={-1} onChange={(e) => setCenter(parseInt(e.target.value))}>
                                {centers.map(one => 
                                <option value={one.id} key={one.id}>
                                    {one.name}
                                </option>)}
                            </Select>
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


export default WorkerRegistrationForm