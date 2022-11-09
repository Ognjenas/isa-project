export interface ValidationField {
    field: string,
    ref: any,
    validations: any[]
}


export interface ValidationErrorOutput {
    isValid: boolean
    errors: string[]
}

export class FormValidator {

    private fields: ValidationField[] = []
    errors: Map<string, ValidationErrorOutput> = new Map<string, ValidationErrorOutput>()

    constructor(fields: ValidationField[]) {
        this.fields = fields;
        this.constructErrorObject()
    }

    private constructErrorObject() {
        this.fields.forEach(obj => this.errors.set(obj.field, { isValid: true, errors: [] }))
    }

    static isRequired(field: string, value: string) {
        const isValid = value.trim() !== ''
        if (!isValid) {
            let message = field + " is required!"
            return message.charAt(0).toUpperCase() + message.slice(1);
        }
        return null
    }

    static isEmail(field: string, value: string) {
        let emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
        if (!value.match(emailRegex)) return "Field should be email!"
        return null
    }

    private validateField(obj: ValidationField) {
        let errorList: string[] = []
        obj.validations.forEach(validation => {
            const validationMessage = validation(obj.field, obj.ref)
            if (validationMessage)
                errorList.push(validationMessage)
        })

        const validationOutput: ValidationErrorOutput = {
            errors: errorList,
            isValid: errorList.length == 0
        }
        this.errors.set(obj.field, validationOutput)
    }


    validate() {
        this.fields.forEach(field => this.validateField(field))
        return Object.fromEntries(this.errors)
    }

    getErrors() {
        return Object.fromEntries(this.errors)
    }

}