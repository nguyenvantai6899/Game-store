import { User } from "./user";

export interface Auth {
    userInfo: User;
    token: string;
}
