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
    private validationPassed: boolean = true
    private errors: Map<string, ValidationErrorOutput> = new Map<string, ValidationErrorOutput>()

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

    static isCenterSelected(sel: number) {
        return () => {
            const isValid = sel >= 0
            if (!isValid) {
                let message = "A center has to be picked"
                return message
            }
            return null
        }
    }

    static minLength(len: number) {
        return (field: string, value: string) => {
            const isValid = value.trim().length >= len
            if (!isValid) {
                let message = "Minimum length is " + len + " characters!"
                return message
            }
            return null
        }
    }

    static maxLength(len: number) {
        return (field: string, value: string) => {
            const isValid = value.trim().length <= len
            if (!isValid) {
                let message = "Maximum length is " + len + " characters!"
                return message
            }
            return null
        }
    }

    static isEqualToField(foreignRef: any, foreignName: string) {
        return (field: string, value: string) => {
            const isValid = value.trim() === foreignRef.trim()
            if (!isValid) {
                let message = field + " doesn't match " + foreignName
                return message.charAt(0).toUpperCase() + message.slice(1);
            }
            return null
        }
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

        if (this.validationPassed && !validationOutput.isValid) this.validationPassed = false
        this.errors.set(obj.field, validationOutput)
    }


    validate() {
        this.validationPassed = true
        this.fields.forEach(field => this.validateField(field))
        return { errors: Object.fromEntries(this.errors), validationPassed: this.validationPassed }
    }

    getErrors() {
        return Object.fromEntries(this.errors)
    }

    didValidationPass() {
        return this.validationPassed
    }

}