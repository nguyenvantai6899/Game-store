export interface User {
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    DOB: string;
    address: string;
    phoneNumber: string;
    role: Role;
}

export interface Role {
    id: number;
    role: string;
}

export interface UserDTO {
    email: string;
    firstName: string;
    lastName: string;
    DOB: string;
    address: string;
    phoneNumber: string;
}
