import { Flex, FormControl, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { CenterRegistrationDTO } from "../../dtos/center-registration.dto"
import { toast } from "react-toastify"
import TemplateForm from "../../../shared/components/template-form"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import { FormValidator, ValidationField } from "../../../shared/utils/form.validator"
import { centerService } from "../../services/center.service"

export const CenterRegistrationForm = () => {

    const [name, setName] = useState("")
    const [city, setCity] = useState("")
    const [country, setCountry] = useState("")
    const [number, setStreetNumber] = useState("")
    const [street, setStreet] = useState("")
    const [description, setDescription] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async () => {
        const dto: CenterRegistrationDTO = {
            name,
            address: {
                country,
                city,
                street,
                number
            },
            description
        }
        let ok = await centerService.registrate(dto)
        if (ok) {
            setTimeout(() => navigate("/centers"), 3000)
        }
    }

    const fields: ValidationField[] = [
        {
            field: 'name',
            ref: name,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'description',
            ref: description,
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
            ref: number,
            validations: [FormValidator.isRequired]
        },

    ]

    let [errors, valid] = useValidator(fields)

    return (
        <Flex margin='auto' justifyContent='center' width='100%' className="register-center-form" border='1px solid lightgray' w={600} p={20}>
            <TemplateForm header={"Center registration"} buttonText={"Save"} onSubmit={handleSubmit}>
                <>
                    <TemplateErrorInput
                        label={'Name'}
                        isValid={errors.name.isValid}
                        error={errors.name.errors[0]}
                        onChange={(e) => setName(e.target.value)}
                        value={name} />
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
                                value={number} />
                        </GridItem>
                    </Grid>
                    <TemplateErrorInput
                        label={'Description'}
                        isValid={errors.description.isValid}
                        error={errors.description.errors[0]}
                        onChange={(e) => setDescription(e.target.value)}
                        value={description} />
                </>
            </TemplateForm>
        </Flex>
    )
}

export default CenterRegistrationForm