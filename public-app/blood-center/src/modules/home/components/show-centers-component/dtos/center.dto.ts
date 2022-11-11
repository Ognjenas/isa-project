import { AddressDTO } from "../../../../auth/dtos/address.dto";

export interface CenterDto {
    name: string;
    description: string;
    averageGrade: number;
    address: AddressDTO;
}