import {useEffect, useState} from "react";
import {workerService} from "../../services/worker.service";
import {Box, Flex, Heading} from "@chakra-ui/react";
import {QApair} from "../../../blood_donation/components/create-donation/create-blood-donation-dto";

interface BloodStorageInstance{
    bloodType: string,
    quantity: number
}

export const BloodStorageComponent = () => {

    const [bloodStorage,setBloodStorage] = useState<BloodStorageInstance[]>([])

    useEffect(() => {
        handleOnMounted()
    }, []);

    const handleOnMounted = async () => {
        let response = await workerService.getBloodStorage()
        setBloodStorage(response.bloodStorage)
    }

    return (
        <Flex
            margin="auto"
            justifyContent="center"
            width="100%"
            border="1px solid lightgray"
            flexDirection="column"
            w={600}
            p={20}
        >
            <Flex direction='column' alignItems='center' justifyContent='center' gap={5} height="100%" width="100%" maxW={500}>
                <Flex justifyContent='flex-start' width='100%'>
                    <Heading as='h2'>Blood Storage</Heading>
                </Flex>
                <Flex direction='column' alignItems='center' justifyContent='center' width='100%' gap={5}>
                    {
                      bloodStorage.map((bsInstance)=>{
                          return (
                              <Flex
                                  justifyContent="space-between">
                                  <Box textAlign="left">{bsInstance.bloodType} </Box>
                                  <span> -{'>'} {bsInstance.quantity} ML</span>
                              </Flex>
                          )
                      })
                    }
                </Flex>
            </Flex>
        </Flex>
    )

}


export default BloodStorageComponent