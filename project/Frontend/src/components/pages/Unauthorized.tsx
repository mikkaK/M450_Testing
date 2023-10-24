import {NavLink} from "react-router-dom";
import {Typography} from "@mui/material";
import Button from "@mui/material/Button";

//The unauthorized page used if a user doesn't have the permission to view a site
export default function Unauthorized() {
    return (
        <>
            <h1>Unauthorized</h1>
            <Button
                className={"userButton"}
                color='info'
                variant='contained'
                sx={{mx: .5}}
            >
                <NavLink to={"/"} className={"userButtonText"}>
                    <Typography
                        variant="body2"
                        fontWeight={"bold"}
                        className={"userButtonText"}
                    >
                        Home
                    </Typography>
                </NavLink>
            </Button>
        </>

    );
}