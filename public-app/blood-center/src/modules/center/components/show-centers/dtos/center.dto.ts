import { AddressDTO } from "../../../../auth/dtos/address.dto";

export interface CenterDto {
    id: number;
    name: string;
    description: string;
    averageGrade: number;
    address: AddressDTO;
}