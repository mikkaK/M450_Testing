import {Box} from '@mui/system';
import logo from '../../logo1.png';
import React from "react";
import {Typography} from "@mui/material";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";

//The home page that is visible to everyone and the first page you get to once you click on our site
export default function HomePage() {
    const navigate = useNavigate();

    const login = () => {
        navigate('../login/');
    };

    return (
        <Box
            display='flex'
            alignItems='center'
            justifyContent='center'
            flexDirection={'column'}
        >
            <h1>Welcome to the Homepage</h1>
            <img
                src={logo}
                style={{filter: 'invert(100%)'}}
                className='App-logo'
                alt='logo'
            />

            <Button
                className={"userButton"}
                color='success'
                variant='contained'
                onClick={login}
            >
                <Typography
                    variant="body2"
                    fontWeight={"bold"}
                    className={"userButtonText"}
                >
                    Login
                </Typography>
            </Button>
        </Box>
    );
}
