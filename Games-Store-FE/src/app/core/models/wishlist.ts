import { Product } from "./product";
import { User } from "./user";

export interface Wishlist {
    id: number;
    product: Product;
    User: User;
}