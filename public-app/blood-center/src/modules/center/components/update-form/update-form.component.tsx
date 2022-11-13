import { Flex, FormControl, FormErrorMessage, FormHelperText, FormLabel, Grid, GridItem, Input, Radio, RadioGroup, Stack } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import TemplateErrorInput from "../../../shared/components/template-form/components/template-error-input"
import TemplateErrorRadio from "../../../shared/components/template-form/components/template-error-radio"
import TemplateForm from "../../../shared/components/template-form"
import { Sex } from "../../../shared/utils/constants"
import { useValidator } from "../../../shared/utils/form-validator.hook"
import { FormValidator, ValidationField } from "../../../shared/utils/form.validator"
// import { UpdateProfileDTO } from "../../dtos/update-profile.dto"
// import { profileService } from "../../services/profile.service"
import { useNavigate } from "react-router-dom"

export const UpdateCenterForm = () =>{

    //FIXME: DODATI I POLJA ZA RADNIKE KAO I ZA VREME!
    const [id,setId]=useState(0)
    const[name,setName]=useState("")
    const[description,setDescription]=useState("")
    const[averageGrade,setAverageGrade]=useState(0)
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")
    const [street, setStreet] = useState("")
    const [streetNumber, setStreetNumber] = useState("")
    const [addressId, setAddressId] = useState(0)

    const handleOnMounted = () => {

    }

    const handleSubmit = async () =>{

    }

    return(
    <Flex margin='auto' justifyContent='center' width='100%' className="update-profile-form" border='1px solid lightgray' w={600} p={20}>
        <TemplateForm header={"Update Profile"} buttonText={"Save"} onSubmit={handleSubmit}>
            <>
                <h1>UPDATE CENTER</h1>

            </>
        </TemplateForm>
    </Flex>
    )
}

export default UpdateCenterForm