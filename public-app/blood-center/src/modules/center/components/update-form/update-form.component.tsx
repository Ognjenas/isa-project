import {
    Flex,
    FormControl,
    FormErrorMessage,
    FormHelperText,
    FormLabel,
    Grid,
    GridItem,
    Input,
    Radio,
    RadioGroup,
    Stack,
} from "@chakra-ui/react"
import { useEffect, useState } from "react"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateForm from "../../../shared/components/template-form"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import {
    FormValidator,
    ValidationField,
} from "../../../shared/utils/form.validator"
import { UpdateCenterDto } from "../../dtos/update-center.dto"
import { centerService } from "../../services/center.service"
import { useNavigate, useParams } from "react-router-dom"
import SingleTimeDisplay from "../single-time-display/single-time-display.component"
import { WorkTimeDto } from "../../dtos/work-time.dto"

export const UpdateCenterForm = () => {
    const { cid } = useParams()
    const [id, setId] = useState(cid ? +cid : -1)
    const [name, setName] = useState("")
    const [description, setDescription] = useState("")
    const [averageGrade, setAverageGrade] = useState(0)
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")
    const [street, setStreet] = useState("")
    const [streetNumber, setStreetNumber] = useState("")
    const [addressId, setAddressId] = useState(0)
    const [workTimes, setWorkTimes] = useState<any>({
        monday: { id: 0, startTime: "10:30", endTime: "10:30" },
        tuesday: { id: 0, startTime: "10:30", endTime: "10:30" },
        wednesday: { id: 0, startTime: "10:30", endTime: "10:30" },
        thursday: { id: 0, startTime: "10:30", endTime: "10:30" },
        friday: { id: 0, startTime: "10:30", endTime: "10:30" },
        saturday: { id: 0, startTime: "10:30", endTime: "10:30" },
        sunday: { id: 0, startTime: "10:30", endTime: "10:30" },
    })
    const navigate = useNavigate()

    const handleOnMounted = async () => {
        let center = await centerService.getCenter(id)
        setName(center.name)
        setDescription(center.description)
        setAverageGrade(center.averageGrade)
        setCountry(center.address.country)
        setCity(center.address.city)
        setStreet(center.address.street)
        setStreetNumber(center.address.number)
        setAddressId(center.address.id)
        let day: any = {}
        for (let time of center.workTime) {
            day[time.day.toLowerCase()] = time
        }
        setWorkTimes({ ...workTimes, ...day })
    }

    const fields: ValidationField[] = [
        {
            field: "name",
            ref: name,
            validations: [FormValidator.isRequired],
        },
        {
            field: "description",
            ref: description,
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
            field: "streetNumber",
            ref: streetNumber,
            validations: [FormValidator.isRequired],
        },
    ]

    let [errors, valid] = useValidator(fields)

    useEffect(() => {
        handleOnMounted()
    }, [])

    const handleSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }

        let workTime = Object.keys(workTimes).map((key) => workTimes[key])
        console.log(workTime)

        const dto: UpdateCenterDto = {
            id,
            name,
            description,
            address: {
                id: addressId,
                country,
                city,
                street,
                number: streetNumber,
            },
            workTime,
        }
        let ok = await centerService.updateCenter(dto)
        if (ok) {
            setTimeout(() => navigate("/centers"), 3000)
        }
    }

    const onChangeTimeFrom = (e: any, key: any) => {
        console.log("Changed " + key + " FROM " + e)
        let day = workTimes[key]
        let time = e.split(":")
        day.startTime = [Number(time[0]), Number(time[1])]
        workTimes[key] = day
        setWorkTimes({ ...workTimes })
    }
    const onChangeTimeTo = (e: any, key: any) => {
        console.log("Changed " + key + " TO " + e)
        let day = workTimes[key]
        let time = e.split(":")
        day.endTime = [Number(time[0]), Number(time[1])]
        workTimes[key] = day
        setWorkTimes({ ...workTimes })
    }

    // @ts-ignore
    return (
        <Flex
            margin="auto"
            justifyContent="center"
            width="100%"
            className="update-profile-form"
            border="1px solid lightgray"
            marginTop="700px"
            w={600}
            p={20}
        >
            <TemplateForm
                header={"Update Center"}
                buttonText={"Save"}
                onSubmit={handleSubmit}
            >
                <>
                    <TemplateErrorInput
                        isValid={errors.name.isValid}
                        error={errors.name.errors[0]}
                        value={name}
                        label={"Name"}
                        onChange={(e) => setName(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.description.isValid}
                        error={errors.description.errors[0]}
                        value={description}
                        label={"Description"}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                    <FormControl>
                        <FormLabel>Average Grade</FormLabel>
                        <Input
                            h={55}
                            fontSize={18}
                            value={averageGrade}
                            readOnly={true}
                        />
                    </FormControl>
                    <h2>Address</h2>
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
                                isValid={errors.streetNumber.isValid}
                                error={errors.streetNumber.errors[0]}
                                onChange={(e) =>
                                    setStreetNumber(e.target.value)
                                }
                                value={streetNumber}
                            />
                        </GridItem>
                    </Grid>
                    <h2>Work Time</h2>
                    <Grid
                        templateColumns="repeat(1, 1fr)"
                        templateRows="repeat(1, 1fr)"
                        gap={5}
                        width="100%"
                    >
                        {
                            // Object.keys(workTimes).map((key,index) =><p key={index}>{JSON.stringify(workTimes[key])}</p> )
                            Object.keys(workTimes).map((key, index) => (
                                <GridItem key={index}>
                                    <SingleTimeDisplay
                                        time={workTimes[key]}
                                        onChangeFrom={(e) =>
                                            onChangeTimeFrom(
                                                e.target.value,
                                                key
                                            )
                                        }
                                        onChangeTo={(e) =>
                                            onChangeTimeTo(e.target.value, key)
                                        }
                                    />
                                </GridItem>
                            ))
                        }
                    </Grid>
                </>
            </TemplateForm>
        </Flex>
    )
}

export default UpdateCenterForm
