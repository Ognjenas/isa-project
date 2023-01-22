import {
    Flex,
    FormControl,
    FormHelperText,
    FormLabel,
    Grid,
    GridItem,
    Input,
    Radio,
    RadioGroup,
    Stack,
} from "@chakra-ui/react"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify"
import { useAuthStore } from "../../../../stores/auth-store/auth.store"
import TemplateForm from "../../../shared/components/template-form"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateErrorRadio from "../../../shared/components/template-form/components/template-error-radio"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import {
    FormValidator,
    ValidationField,
} from "../../../shared/utils/form.validator"
import { AuthRequestDTO } from "../../dtos/authRequest.dto"
import { RegistrationDTO } from "../../dtos/registration.dto"
import { authService } from "../../services/auth.service"

enum Sex {
    MALE = "MALE",
    FEMALE = "FEMALE",
}

export const LoginForm = () => {
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
    const navigate = useNavigate()
    const login = useAuthStore((state) => state.login)
    const handleSubmit = async () => {
        const dto: AuthRequestDTO = {
            email,
            password,
        }

        const ok = await login(dto)

        if (ok) {
            setTimeout(() => navigate("/users"), 200)
        }
    }

    const fields: ValidationField[] = [
        {
            field: "email",
            ref: email,
            validations: [FormValidator.isEmail, FormValidator.isRequired],
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
                header={"Login"}
                buttonText={"login"}
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
                </>
            </TemplateForm>
        </Flex>
    )
}

export default LoginForm
