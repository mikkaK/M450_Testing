import {useFormik} from 'formik';
import {UserProfile} from '../../../types/models/UserProfile.model';
import {Box, Button, Card, CardContent, Grid, Paper, TextField, Typography} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import {date, number, object, string} from 'yup';
import moment from 'moment';
import activeUserContext from "../../../Contexts/ActiveUserContext";
import {useContext} from "react";
import authorities from "../../../config/Authorities";

interface UserProfileProps {
    userProfile: UserProfile;
    submitActionHandler: (values: UserProfile) => void;
}

//The form used to create or edit a userProfile
const UserProfileForm = ({userProfile, submitActionHandler}: UserProfileProps) => {
    const navigate = useNavigate();
    const context = useContext(activeUserContext);

    const formik = useFormik({
        initialValues: {
            id: userProfile.id,
            age: userProfile ? userProfile.age : 1,
            profilePictureURL: userProfile ? userProfile.profilePictureURL : '',
            address: userProfile ? userProfile.address : '',
            birthDate: userProfile ? userProfile.birthDate : moment().format('YYYY-MM-DD').toString(),
        },
        validationSchema: object({
            age: number().required(),
            profilePictureURL: string().required().url(),
            address: string().required().min(10).max(30),
            birthDate: date().required(),
        }),
        onSubmit: (values: UserProfile) => {
            submitActionHandler(values);
        },
        enableReinitialize: true,
    });

    //To create a userProfile for a user that does not have a profile yet
    if (window.location.href === "http://localhost:3000/userprofileedit/") {
        return (
            <>
                <Grid container>
                    <Grid item md={4}/>
                    <Grid item xs={12} md={4} p={1}>
                        <Typography sx={{fontWeight: 600}} variant="h4">
                            User Profile Form
                        </Typography>
                        <form onSubmit={formik.handleSubmit}>
                            <Card className={"userCard"}>
                                <CardContent>
                                    <Box>
                                        <Typography sx={{fontWeight: 600}}>
                                            Age
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='age'
                                            variant='outlined'
                                            className="input"
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.age && formik.errors.age)}
                                            value={formik.values.age}
                                        />
                                        {formik.errors.age && formik.touched.age ? (
                                            <div style={{color: 'red'}}>{formik.errors.age}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Profile Picture URL
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='profilePictureURL'
                                            variant='outlined'
                                            className="input"
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.profilePictureURL && formik.errors.profilePictureURL)}
                                            value={formik.values.profilePictureURL}
                                        />
                                        {formik.errors.profilePictureURL && formik.touched.profilePictureURL ? (
                                            <div style={{color: 'red'}}>{formik.errors.profilePictureURL}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Address
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='address'
                                            className="input"
                                            variant='outlined'
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.address && formik.errors.address)}
                                            value={formik.values.address}
                                        />

                                        {formik.errors.address && formik.touched.address ? (
                                            <div style={{color: 'red'}}>{formik.errors.address}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Birth Date
                                        </Typography>
                                        <TextField
                                            id='birthDate'
                                            className="input"
                                            variant='outlined'
                                            type="date"
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.birthDate && formik.errors.birthDate)}
                                            value={formik.values.birthDate}
                                        />
                                        {formik.errors.birthDate && formik.touched.birthDate ? (
                                            <div style={{color: 'red'}}>{formik.errors.birthDate as string}</div>
                                        ) : null}
                                    </Box>
                                    <div>
                                        <Button
                                            sx={{marginTop: '15px'}}
                                            className={"userButton"}
                                            variant='contained'
                                            color='error'
                                            onClick={() => {
                                                // @ts-ignore because the role of the user could be null but we catch that with the else here
                                                if (context.user.roles.map((element) => element.name).includes(authorities.USER_MODIFY) || context.user.roles.map((element) => element.name).includes(authorities.USER_DELETE)) {
                                                    navigate('/authHomeAdmin');
                                                } else {
                                                    navigate('/authHomeUser');
                                                }
                                            }}
                                        >
                                            <Typography
                                                variant="body2"
                                                fontWeight={"bold"}
                                                className={"userButtonText"}
                                            >
                                                Cancel
                                            </Typography>
                                        </Button>
                                        <Button
                                            sx={{marginTop: '15px', marginRight: '10px'}}
                                            className={"userButton"}
                                            variant='contained'
                                            color='success'
                                            type='submit'
                                            disabled={!(formik.dirty && formik.isValid)}
                                        >
                                            <Typography
                                                variant="body2"
                                                fontWeight={"bold"}
                                                className={"userButtonText"}
                                            >
                                                {userProfile.id && 'Save'}
                                                {!userProfile.id && 'Finish'}
                                            </Typography>
                                        </Button>
                                    </div>
                                </CardContent>
                            </Card>
                        </form>
                    </Grid>
                    <Grid item md={4}/>
                </Grid>
            </>
        );
        //to update the userProfile of a user that already has one
    } else {
        return (
            <>
                <Grid container>
                    <Grid item md={4}/>
                    <Grid item xs={12} md={4} p={1}>
                        <Typography variant="h3" sx={{fontWeight: 600, mt: 2}}>
                            Your Profile
                        </Typography>
                        <Card className={"userCard"} sx={{boxShadow: "none"}}>
                            <CardContent>
                                <Typography
                                    gutterBottom
                                    variant="h5"
                                    fontWeight={"bold"}
                                    component="div"
                                >
                                    User: {userProfile.user?.firstName} {userProfile.user?.lastName}
                                </Typography>
                                <Typography
                                    gutterBottom
                                    variant="body2"
                                    fontWeight={"bold"}
                                    component="div"
                                >
                                    Age:
                                </Typography>
                                <Paper elevation={1} sx={{padding: "3px"}}>
                                    <Typography
                                        gutterBottom
                                        variant="body2"
                                        fontWeight={"bold"}
                                        component="div"
                                    >
                                        {userProfile.age}
                                    </Typography>
                                </Paper>
                                <Typography
                                    gutterBottom
                                    variant="body2"
                                    fontWeight={"bold"}
                                    component="div"
                                >
                                    ProfilePictureURL:
                                </Typography>
                                <Paper elevation={1} sx={{padding: "3px"}}>
                                    <Typography
                                        gutterBottom
                                        variant="body2"
                                        fontWeight={"bold"}
                                        component="div"
                                    >
                                        {userProfile.profilePictureURL}
                                    </Typography>
                                </Paper>
                                <Typography
                                    gutterBottom
                                    variant="body2"
                                    fontWeight={"bold"}
                                    component="div"
                                >
                                    Address:
                                </Typography>
                                <Paper elevation={1} sx={{padding: "3px"}}>
                                    <Typography
                                        gutterBottom
                                        variant="body2"
                                        fontWeight={"bold"}
                                        component="div"
                                    >
                                        {userProfile.address}
                                    </Typography>
                                </Paper>
                                <Typography
                                    gutterBottom
                                    variant="body2"
                                    fontWeight={"bold"}
                                    component="div"
                                >
                                    Birth Date:
                                </Typography>
                                <Paper elevation={1} sx={{padding: "3px"}}>
                                    <Typography
                                        gutterBottom
                                        variant="body2"
                                        fontWeight={"bold"}
                                        component="div"
                                    >
                                        {userProfile.birthDate.toString()}
                                    </Typography>
                                </Paper>

                            </CardContent>
                        </Card>
                        <Typography variant="h4" sx={{fontWeight: 600, mt: 2}}>
                            Edit Profile
                        </Typography>
                        <form onSubmit={formik.handleSubmit}>
                            <Card className={"userCard"}>
                                <CardContent>
                                    <Box>
                                        <Typography sx={{fontWeight: 600}}>
                                            Age
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='age'
                                            variant='outlined'
                                            className="input"
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.age && formik.errors.age)}
                                            value={formik.values.age}
                                        />
                                        {formik.errors.age && formik.touched.age ? (
                                            <div style={{color: 'red'}}>{formik.errors.age}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Profile Picture URL
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='profilePictureURL'
                                            variant='outlined'
                                            className="input"
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.profilePictureURL && formik.errors.profilePictureURL)}
                                            value={formik.values.profilePictureURL}
                                        />
                                        {formik.errors.profilePictureURL && formik.touched.profilePictureURL ? (
                                            <div style={{color: 'red'}}>{formik.errors.profilePictureURL}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Address
                                        </Typography>
                                        <TextField
                                            fullWidth
                                            id='address'
                                            className="input"
                                            variant='outlined'
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.address && formik.errors.address)}
                                            value={formik.values.address}
                                        />

                                        {formik.errors.address && formik.touched.address ? (
                                            <div style={{color: 'red'}}>{formik.errors.address}</div>
                                        ) : null}
                                        <Typography sx={{fontWeight: 600}}>
                                            Birth Date
                                        </Typography>
                                        <TextField
                                            id='birthDate'
                                            className="input"
                                            variant='outlined'
                                            type="date"
                                            fullWidth
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            error={Boolean(formik.touched.birthDate && formik.errors.birthDate)}
                                            value={formik.values.birthDate}
                                        />
                                        {formik.errors.birthDate && formik.touched.birthDate ? (
                                            <div style={{color: 'red'}}>{formik.errors.birthDate as string}</div>
                                        ) : null}
                                    </Box>
                                    <div>
                                        <Button
                                            sx={{marginTop: '15px'}}
                                            className={"userButton"}
                                            variant='contained'
                                            color='primary'
                                            onClick={() => {
                                                // @ts-ignore because the role of the user could be null but we catch that with the else here
                                                if (context.user.roles.map((element) => element.name).includes(authorities.USER_MODIFY) || context.user.roles.map((element) => element.name).includes(authorities.USER_DELETE)) {
                                                    navigate('/authHomeAdmin');
                                                } else {
                                                    navigate('/authHomeUser');
                                                }
                                            }}
                                        >
                                            <Typography
                                                variant="body2"
                                                fontWeight={"bold"}
                                                className={"userButtonText"}
                                            >
                                                Back
                                            </Typography>
                                        </Button>
                                        <Button
                                            sx={{marginTop: '15px', marginRight: '10px'}}
                                            className={"userButton"}
                                            variant='contained'
                                            color='success'
                                            type='submit'
                                            disabled={!(formik.dirty && formik.isValid)}
                                        >
                                            <Typography
                                                variant="body2"
                                                fontWeight={"bold"}
                                                className={"userButtonText"}
                                            >
                                                Save
                                            </Typography>
                                        </Button>
                                    </div>
                                </CardContent>
                            </Card>
                        </form>
                    </Grid>
                    <Grid item md={4}/>
                </Grid>
            </>
        );
    }
};

export default UserProfileForm;
