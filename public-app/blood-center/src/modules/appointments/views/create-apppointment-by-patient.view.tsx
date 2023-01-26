import { FormControl, FormErrorMessage, FormLabel } from "@chakra-ui/form-control"
import { Input } from "@chakra-ui/input"
import { Flex } from "@chakra-ui/layout"
import { Select } from "@chakra-ui/select"
import moment from "moment"
import { useEffect, useState } from "react"
import { toast } from "react-toastify"
import { option } from "yargs"
import FilterSelect from "../../center/components/show-centers/components/filter-select"
import { SelectOption } from "../../center/components/show-centers/components/filter-select/filter-select.component"
import { CenterDto } from "../../center/components/show-centers/dtos/center.dto"
import { centerService } from "../../center/components/show-centers/services/center.service"
import TemplateForm from "../../shared/components/template-form"
import TemplateErrorInput from "../../shared/components/template-form/components/template-error-input"
import { useValidator } from "../../shared/utils/form-validator.hook"
import { FormValidator, ValidationField } from "../../shared/utils/form.validator"
import { CreateAppointmentDTO } from "../dtos/create-appointment.dto"
import { appointmentService } from "../services/appointment.service"





export const CreateAppointmentPatientView = () => {

    const [duration, setDuration] = useState(30)
    const [startTime, setStartTime] = useState(new Date())
    const [centers, setCenters] = useState<SelectOption[]>([])
    const [center, setCenter] = useState<number | null>(null)


    const handleSubmit = async () => {
        if (!isValid) {
            toast.error("All validations should be resolved")
            return
        }

        const dto: CreateAppointmentDTO = {
            centerId: center ?? 0,
            duration: duration,
            startTime: startTime
        }

        const success = await appointmentService.createAppointmentPatient(dto)
        if (success)
            toast.success("Appointment succesfully created!")
    }

    const handleCenterSelected = (e: any) => {
        setCenter(+e.target.value)
    }


    const validationFields: ValidationField[] = [
        {
            field: 'startTime',
            ref: startTime,
            validations: [FormValidator.isRequired]
        },
        {
            field: 'duration',
            ref: duration,
            validations: [FormValidator.isRequired, FormValidator.minValue(1)]
        }
    ]

    const [errors, isValid] = useValidator(validationFields)

    const convertDate = (date: Date) => {
        if (!date) return ""
        const dt = new Date(date)
        const tzoffset = (new Date()).getTimezoneOffset() * 60000;
        const localISOTime = moment(new Date(dt.getTime())).format("YYYY-MM-DD HH:mm")
        return localISOTime
    }

    const handleChange = (event: any) => {
        const newDate = new Date(event.target.value)
        setStartTime(newDate);
        loadCenters();
    }

    const loadCenters = async () => {
        const centers: any[] = await centerService.getCentersPatient(startTime)
        const options = centers.map(c => ({ text: c.name, val: c.id }))
        setCenters(options)
    }

    useEffect(() => {
        loadCenters()
    }, [])

    useEffect(() => {
        console.log(center)
    }, [center])


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
                header={"Create Appointment"}
                buttonText={"Save"}
                onSubmit={handleSubmit}
            >
                <>

                    <TemplateErrorInput
                        isValid={errors.startTime.isValid}
                        error={errors.startTime.errors[0]}
                        value={convertDate(startTime)}
                        label={"Time"}
                        type="datetime-local"
                        onChange={handleChange}
                    />
                    <TemplateErrorInput
                        isValid={errors.duration.isValid}
                        error={errors.duration.errors[0]}
                        value={duration + ""}
                        label={"Duration"}
                        onChange={(e) => setDuration(e.target.value)}
                    />

                    <FormControl>
                        <FormLabel>{"Center"}</FormLabel>
                        <Select
                            onChange={handleCenterSelected}
                            placeholder="Choose center"
                        >
                            {
                                centers.map(center => (<option key={center.val} value={center.val}>{center.text}</option>))
                            }
                        </Select>
                    </FormControl>
                </>
            </TemplateForm>
        </Flex>
    )

}


export default CreateAppointmentPatientView