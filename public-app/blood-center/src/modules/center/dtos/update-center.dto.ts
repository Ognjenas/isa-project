import {Address} from "../../profiles/model/address";
import {WorkTimeDto} from "./work-time.dto";

export interface UpdateCenterDto{
    id:number
    name: string
    description: string
    address: Address
    workTime : WorkTimeDto[]
}
