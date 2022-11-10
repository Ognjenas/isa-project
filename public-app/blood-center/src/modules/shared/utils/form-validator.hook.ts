import { useEffect, useState } from "react";
import { FormValidator, ValidationField } from "./form.validator";





export const useValidator = (fields: ValidationField[]): [any, boolean] => {
    const validator = new FormValidator(fields)
    const refs = fields.map(field => field.ref)
    const [errors, setErrors] = useState(validator.getErrors())
    const [valid, setValid] = useState(true)


    useEffect(() => {
        const { errors, validationPassed } = validator.validate()
        setErrors(errors)
        setValid(validationPassed)
    }, [...refs])

    return [errors, valid];

}