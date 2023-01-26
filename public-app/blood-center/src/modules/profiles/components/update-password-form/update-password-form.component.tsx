import React, {useEffect, useState} from "react";
import {Flex} from "@chakra-ui/react";
import TemplateForm from "../../../shared/components/template-form";
import {FormValidator, ValidationField} from "../../../shared/utils/form.validator";
import {useValidator} from "../../../shared/utils/form-validator.hook";
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input";
import {profileService} from "../../services/profile.service";
import {useNavigate} from "react-router-dom";

export interface UpdatePasswordDto {
    oldPassword:string
    newPassword:string
}
export const UpdatePasswordForm = () =>{

    useEffect(() => {}, [])

    const [oldPassword, setOldPassword] = useState("")
    const [newPassword, setNewPassword] = useState("")
    const navigate = useNavigate()

    const fields: ValidationField[] = [
        {
            field: 'oldPassword',
            ref: oldPassword,
            validations: [FormValidator.isRequired,
                            FormValidator.minLength(8),
                            FormValidator.maxLength(20)]
        },
        {
            field: 'newPassword',
            ref: newPassword,
            validations: [FormValidator.isRequired,
                            FormValidator.minLength(8),
                            FormValidator.maxLength(20)]
        }
        ]

    let [errors, valid] = useValidator(fields)

    const handleSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }
        const dto:UpdatePasswordDto = {
            oldPassword,
            newPassword
        }

        let ok = await profileService.updatePassword(dto)
        if (ok) {
            setTimeout(() => navigate("/users"), 3000)
        }

    }


    return (
        <Flex margin='auto' borderRadius={10} justifyContent='center' width='100%' className="update-profile-form"
              border='1px solid lightgray' w={700} p={10}>
            <TemplateForm header={"Password Change"} buttonText={"Update"} onSubmit={handleSubmit}>
                <>
                    <TemplateErrorInput
                        label={'Old Password'}
                        isValid={errors.oldPassword.isValid}
                        error={errors.oldPassword.errors[0]}
                        onChange={(e) => setOldPassword(e.target.value)}
                        value={oldPassword}/>
                    <TemplateErrorInput
                        label={'New Password'}
                        isValid={errors.newPassword.isValid}
                        error={errors.newPassword.errors[0]}
                        onChange={(e) => setNewPassword(e.target.value)}
                        value={newPassword}/>
                </>
            </TemplateForm>
        </Flex>
        )

}

export default UpdatePasswordForm