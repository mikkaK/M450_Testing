import api from '../config/Api';
import {AxiosResponse} from 'axios';
import {UserProfile} from '../types/models/UserProfile.model';
import {UserProfileDTO} from '../types/models/UserProfileDTO.model';

//The user profile service used to use the functionality from the backend for the CRUD methods
const UserProfileService = {
    getUserProfile: async (userProfileID: string): Promise<UserProfile> => {
        const {data} = await api.get<UserProfile>(`/userprofile/${userProfileID}`);
        return data;
    },

    updateUserProfile: (id: string, userProfileDTO: UserProfileDTO) => {
        return api.put(`/userprofile/${id}`, userProfileDTO);
    },

    addUserProfile: (userProfileDTO: UserProfileDTO) => {
        return api.post('/userprofile', userProfileDTO).then((res) => {
            return res.data;
        });
    },

    getAllUserProfiles: (pageSize: number, page: number, sortBy: string, asc: boolean): Promise<AxiosResponse<UserProfile[]>> => {
        return api.get<UserProfile[]>('/userprofile', {
            params: {
                pageSize: pageSize,
                page: page,
                sortBy: sortBy,
                asc: asc
            },
        });
    },

    deleteUserProfile: (id: string) => {
        return api.delete(`/userprofile/${id}`);
    },
};

export default UserProfileService;
