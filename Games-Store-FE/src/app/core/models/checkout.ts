import { Product } from "./product";
import { User, UserDTO } from "./user";

export interface Checkout {
    id: number;
    productCheckout: Product[];
    user: UserDTO;
    paymentMethod: string;
    paymentDate: Date;
    amount: number;
    status: boolean;
}