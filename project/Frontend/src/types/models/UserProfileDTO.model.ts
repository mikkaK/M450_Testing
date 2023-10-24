import * as moment from "moment";
import {User} from "./User.model";

export type UserProfileDTO = {
    age: number;
    profilePictureURL: string;
    address: string;
    birthDate: moment.Moment | string;
    user: User;
};
