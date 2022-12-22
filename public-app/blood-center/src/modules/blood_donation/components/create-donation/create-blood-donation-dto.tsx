import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {FormValidator, ValidationField} from "../../../shared/utils/form.validator";
import {useValidator} from "../../../shared/utils/form-validator.hook";
import {Button, Flex, FormControl, FormLabel, Grid, GridItem, Input} from "@chakra-ui/react";
import TemplateForm from "../../../shared/components/template-form";
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input";
import {centerService} from "../../../center/services/center.service";
import {bloodDonationService} from "../../services/blood_donation.service";

export interface CreateBloodDonationDto {
    appointmentId:number
    userId:number
    bloodType: number
    hemoglobin: number
    copperSulfate: number
    heartRateUpper: number
    heartRateLower : number
    donatedAmount : number
    donationIsApproved : boolean
    didntShowUp : boolean
}

export const CreateBloodDonationForm = () => {
    const { cid } = useParams()
    const [id, setId] = useState(cid ? +cid : -1)
    const [userId, setUserId] = useState(0)
    const [bloodType, setBloodType] = useState(0)
    const [hemoglobin, setHemoglobin] = useState(0)
    const [copperSulfate, setCopperSulfate] = useState(0)
    const [heartRateUpper, setHeartRateUpper] = useState(0)
    const [heartRateLower, setHeartRateLower] = useState(0)
    const [donationIsApproved, setDonationIsApproved] = useState(false)
    const [didntShowUp, setDidntShowUp] = useState(false)
    const [donatedAmount, setDonatedAmount] = useState(0)
    const navigate = useNavigate()



    const fields: ValidationField[] = [
        {
            field: "userId",
            ref: userId,
            validations: [FormValidator.isRequired],
        },
        {
            field: "bloodType",
            ref: bloodType,
            validations: [FormValidator.isRequired],
        },
        {
            field: "hemoglobin",
            ref: hemoglobin,
            validations: [FormValidator.isRequired],
        },
        {
            field: "copperSulfate",
            ref: copperSulfate,
            validations: [FormValidator.isRequired],
        },
        {
            field: "heartRateUpper",
            ref: heartRateUpper,
            validations: [FormValidator.isRequired],
        },
        {
            field: "heartRateLower",
            ref: heartRateLower,
            validations: [FormValidator.isRequired],
        },
        {
            field: "donatedAmount",
            ref: donatedAmount,
            validations: [FormValidator.isRequired],
        },
    ]

    let [errors, valid] = useValidator(fields)

    useEffect(() => {}, [])

    const handleSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }

        const dto:CreateBloodDonationDto = {
            appointmentId:id,
            bloodType,
            userId,
            hemoglobin,
            copperSulfate,
            heartRateUpper,
            heartRateLower,
            donatedAmount,
            donationIsApproved:true,
            didntShowUp:false,
        }

        let ok = await bloodDonationService.createBloodDonation(dto)
        if (ok) {
            setTimeout(() => navigate("/centers"), 3000)
        }
    }
    const didntShowUpSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }

        const dto:CreateBloodDonationDto = {
            appointmentId:id,
            userId,
            bloodType:0,
            hemoglobin:0,
            copperSulfate:0,
            heartRateUpper:0,
            heartRateLower:0,
            donatedAmount:0,
            donationIsApproved:false,
            didntShowUp:true,
        }

        let ok = await bloodDonationService.createBloodDonation(dto)
        if (ok) {
            setTimeout(() => navigate("/centers"), 3000)
        }
    }
    const declinedSubmit = async () => {
        if (!valid) {
            console.log("Sorry validation not passed")
            return
        }

        const dto:CreateBloodDonationDto = {
            appointmentId:id,
            userId,
            bloodType:0,
            hemoglobin:0,
            copperSulfate:0,
            heartRateUpper:0,
            heartRateLower:0,
            donatedAmount:0,
            donationIsApproved:false,
            didntShowUp:false,
        }

        let ok = await bloodDonationService.createBloodDonation(dto)
        if (ok) {
            setTimeout(() => navigate("/centers"), 3000)
        }
    }

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
                header={"Donate Blood"}
                buttonText={"Create"}
                onSubmit={handleSubmit}
            >
                <>
                    <TemplateErrorInput
                        isValid={errors.userId.isValid}
                        error={errors.userId.errors[0]}
                        value={userId.toString()}
                        label={"UserId"}
                        onChange={(e) => setUserId(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.bloodType.isValid}
                        error={errors.bloodType.errors[0]}
                        value={bloodType.toString()}
                        label={"Blood Type"}
                        onChange={(e) => setBloodType(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.hemoglobin.isValid}
                        error={errors.hemoglobin.errors[0]}
                        value={hemoglobin.toString()}
                        label={"Hemoglobin Amount (ml)"}
                        onChange={(e) => setHemoglobin(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.copperSulfate.isValid}
                        error={errors.copperSulfate.errors[0]}
                        value={copperSulfate.toString()}
                        label={"Copper Sulfate Amount (ml)"}
                        onChange={(e) => setCopperSulfate(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.heartRateUpper.isValid}
                        error={errors.heartRateUpper.errors[0]}
                        value={heartRateUpper.toString()}
                        label={"Heart Rate - UPPER"}
                        onChange={(e) => setHeartRateUpper(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.heartRateLower.isValid}
                        error={errors.heartRateLower.errors[0]}
                        value={heartRateLower.toString()}
                        label={"Heart Rate - LOWER"}
                        onChange={(e) => setHeartRateLower(e.target.value)}
                    />
                    <TemplateErrorInput
                        isValid={errors.donatedAmount.isValid}
                        error={errors.donatedAmount.errors[0]}
                        value={donatedAmount.toString()}
                        label={"Donated Blood Amount (ml)"}
                        onChange={(e) => setDonatedAmount(e.target.value)}
                    />
                    <div>
                        <Button size='lg' onClick={() => didntShowUpSubmit()} colorScheme='blue'>Didn't Come</Button>
                        <Button size='lg' onClick={() => declinedSubmit()} colorScheme='blue'>Decline</Button>

                    </div>
                </>
            </TemplateForm>
        </Flex>
    )
}

export default CreateBloodDonationForm