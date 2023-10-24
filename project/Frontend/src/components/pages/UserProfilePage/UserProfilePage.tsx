import {useNavigate, useParams} from 'react-router-dom';
import UserProfileService from '../../../Services/UserProfileService';
import {useContext, useEffect, useState} from 'react';
import {UserProfile} from "../../../types/models/UserProfile.model";
import UserProfileForm from "../../molecules/UserProfileForm/UserProfileForm";
import Moment from "moment/moment";
import ActiveUserContext from "../../../Contexts/ActiveUserContext";
import {UserProfileDTO} from "../../../types/models/UserProfileDTO.model";
import authorities from "../../../config/Authorities";

const UserProfilePage = () => {
    const context = useContext(ActiveUserContext);
    const navigate = useNavigate();
    const {userProfileId} = useParams();
    const [userProfile, setUserProfile] = useState<UserProfile>({
        id: '',
        age: 1,
        profilePictureURL: '',
        address: '',
        birthDate: Moment().format('YYYY-MM-DD'),
        user: {
            id: '',
        }
    });

    useEffect(() => {
        return () => {
            if (userProfileId) {
                UserProfileService.getUserProfile(userProfileId).then((res) => {
                    console.log(res)
                    return setUserProfile(res);
                });
            }
        };
    }, [userProfileId]);


    const submitActionHandler = (values: UserProfile) => {
        const userProfileDTO: UserProfileDTO = {
            age: values.age,
            profilePictureURL: values.profilePictureURL,
            address: values.address,
            birthDate: values.birthDate,
            user: {
                id: userProfileId ?? context.user!.id,
            }
        };

        //check to do put or post

        if (userProfileId !== undefined) {
            UserProfileService.updateUserProfile(userProfileDTO.user.id, userProfileDTO).then(() => {
                // @ts-ignore because the role of the user could be null but we catch that with the else here
                if (context.user.roles.map((element) => element.name).includes(authorities.USER_MODIFY) || context.user.roles.map((element) => element.name).includes(authorities.USER_DELETE)) {
                    navigate('/authHomeAdmin');
                } else {
                    navigate('/authHomeUser');
                }
            });
        } else {
            UserProfileService.addUserProfile(userProfileDTO).then(() => {
                // @ts-ignore because the role of the user could be null but we catch that with the else here
                if (context.user.roles.map((element) => element.name).includes(authorities.USER_MODIFY) || context.user.roles.map((element) => element.name).includes(authorities.USER_DELETE)) {
                    navigate('/authHomeAdmin');
                } else {
                    navigate('/authHomeUser');
                }
            });
        }
    };
    return <UserProfileForm userProfile={userProfile} submitActionHandler={submitActionHandler}/>;
};
export default UserProfilePage;
