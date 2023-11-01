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
                            <h1>Admin Home</h1>
                            <Button
                                className={"userButton"}
                                variant='contained'
                                onClick={handleAdd}
                            >
                                Profil erstellen
                            </Button>
                            <div className={"margin"}></div>
                            <Button
                                className={"userButton"}
                                variant='contained'
                                onClick={() => handleEdit(context.user!.id)}
                            >
                                Profil bearbeiten
                            </Button>
                            <div className={"margin"}></div>
                            <Button
                                className={"userButton"}
                                variant='contained'
                            >
                                <NavLink to={"/userprofile"} className={"userButtonText"}>
                                        Alle Profile
                                </NavLink>
                            </Button>
                            <div className={"margin"}></div>
                            <Button
                                color={"error"}
                                className={"userButton"}
                                variant='contained'
                                onClick={context.logout}
                            >
                                    Abmelden
                            </Button>
                            <Typography
                                variant="body2"
                                fontWeight={"bold"}
                                sx={{mt: 2}}
                            >
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
        </Box>
    );
}