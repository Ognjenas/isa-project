import {Address} from "../../profiles/model/address";
import {Sex} from "../../shared/utils/constants";


export interface GetWorker2UpdateDto{
    name: string
    surname: string
    sex: Sex
    uid: string
    profession: string
    school: string
    address: Address
    hospitalName: string
}