import {Box} from '@mui/system';
import Button from "@mui/material/Button";
import {NavLink, useNavigate} from "react-router-dom";
import {Card, CardContent, Grid, Typography} from "@mui/material";
import React from "react";
import ActiveUserContext from "../../Contexts/ActiveUserContext";

//The home page for a logged-in admin
export default function HomePageLoggedInAdmin() {

    const context = React.useContext(ActiveUserContext);
    const navigate = useNavigate();

    const handleAdd = () => {
        navigate('/userprofileedit/');
    };

    const handleEdit = (id: string) => {
        navigate('../userprofileedit/' + id);
    };

    return (
        <Box
            display='flex'
            alignItems='center'
            justifyContent='center'
            flexDirection={'column'}
        >
            <Grid container>
                <Grid item md={4} lg={4}/>
                <Grid item md={4} lg={4} p={2}>
                    <Card className={"userCard"} sx={{boxShadow: "none"}}>
                        <CardContent>
                            <h1>Welcome to the Admin Homepage</h1>
                            <Button
                                className={"userButton"}
                                color='success'
                                variant='contained'
                                onClick={handleAdd}
                            >
                                <Typography
                                    variant="body2"
                                    fontWeight={"bold"}
                                    className={"userButtonText"}
                                >
                                    Create a Profile
                                </Typography>
                            </Button>

                            <Button
                                className={"userButton"}
                                color='info'
                                variant='contained'
                                sx={{my: 1}}
                            >
                                <Typography
                                    variant="body2"
                                    fontWeight={"bold"}
                                    className={"userButtonText"}
                                    onClick={() => handleEdit(context.user!.id)}
                                >
                                    Edit Profile
                                </Typography>
                            </Button>

                            <Button
                                className={"userButton"}
                                color='secondary'
                                variant='contained'
                            >
                                <NavLink to={"/userprofile"} className={"userButtonText"}>
                                    <Typography
                                        variant="body2"
                                        fontWeight={"bold"}
                                        className={"userButtonText"}
                                    >
                                        See all Profiles
                                    </Typography>
                                </NavLink>
                            </Button>

                            <Button
                                className={"userButton"}
                                color='error'
                                variant='contained'
                                sx={{my: 1}}
                                onClick={context.logout}
                            >
                                <Typography
                                    variant="body2"
                                    fontWeight={"bold"}
                                    className={"userButtonText"}
                                >
                                    Logout
                                </Typography>
                            </Button>
                            <Typography
                                variant="body2"
                                fontWeight={"bold"}
                                sx={{mt: 2}}
                            >
                                Logged in as: {context.user?.firstName} {context.user?.lastName}
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
        </Box>
    );
}