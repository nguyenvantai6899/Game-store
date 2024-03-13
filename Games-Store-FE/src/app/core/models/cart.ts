import { Product } from "./product";
import { User } from "./user";

export interface Cart {
    id: number;
    products: Product[];
    user: User;
}