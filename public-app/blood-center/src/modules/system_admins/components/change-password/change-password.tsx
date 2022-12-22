import { Flex } from "@chakra-ui/react"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify"
import TemplateForm from "../../../shared/components/template-form"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import {FormValidator, ValidationField} from "../../../shared/utils/form.validator"
import { adminService } from "../../services/admin.service"

export const AdminChangePasswordForm = () => {

    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async () => {
        if (password === confirmPassword) {
            let ok = await adminService.changePassword(password)
            if (ok) {
                setTimeout(() => navigate("/profile"), 3000)//vratiti na login
            }
        } else {
            toast.error("Passwords must be same")
        }
    }

    const fields: ValidationField[] = [
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
                header={"Change password"}
                buttonText={"Save"}
                onSubmit={handleSubmit}
            >
                <>
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
                </>
            </TemplateForm>
        </Flex>
    )

}

export default AdminChangePasswordForm