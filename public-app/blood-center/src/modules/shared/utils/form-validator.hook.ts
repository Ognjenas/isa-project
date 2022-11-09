import { useEffect, useState } from "react";
import { FormValidator, ValidationField } from "./form.validator";





export const useValidator = (fields: ValidationField[]) => {
    const validator = new FormValidator(fields)
    const refs = fields.map(field => field.ref)
    const [errors, setErrors] = useState(validator.getErrors())


    useEffect(() => {
        console.log("triggering")
        setErrors(validator.validate())
    }, [...refs])

    return errors;

}