import {Button, Card, CardContent, Grid, Link, TextField, Typography,} from '@mui/material';
import React, {useContext} from 'react';

import {Form, Formik} from 'formik';
import {useNavigate} from 'react-router-dom';
import * as Yup from 'yup';
import ActiveUserContext from '../../../Contexts/ActiveUserContext';
import activeUserContext from "../../../Contexts/ActiveUserContext";
import authorities from "../../../config/Authorities";

const validationSchema = Yup.object().shape({
    email: Yup.string(),
    password: Yup.string(),
});

const Login = () => {
    const btnstyle = {margin: '8px 0'};
    const navigate = useNavigate();
    const {login} = useContext(ActiveUserContext);
    const context = useContext(activeUserContext);

    const handleSubmit = (values: { email: string; password: string }) => {
        login(values.email.toLowerCase(), values.password)
            .then(() => {
                console.log(values);

                // @ts-ignore because the role of the user could be null but we catch that with the else here
                if (context.user.roles.map((element) => element.name).includes(authorities.USER_MODIFY) || context.user.roles.map((element) => element.name).includes(authorities.USER_DELETE)) {
                    navigate('/authHomeAdmin');
                } else {
                    navigate('/authHomeUser');
                }
            })
            .catch((error) => {
                if (
                    (typeof error.response !== 'undefined' &&
                        error.response.status === 401) ||
                    error.response.status === 403
                ) {
                    alert('invalid login');
                } else {
                    alert('login Error');
                }
            });
    };
    return (
        <Grid container>
            <Grid item md={4}/>
            <Grid item xs={12} md={4} p={1}>
                <Card className={"userCard"}>
                    <CardContent>
                        <Grid>
                            <h2>Sign In</h2>
                            <p>Default login:</p>
                            <p>email: admin@example.com</p>
                            <p>pw: 1234</p>
                        </Grid>

                        <Formik
                            initialValues={{
                                email: '',
                                password: '',
                            }}
                            enableReinitialize
                            validationSchema={validationSchema}
                            onSubmit={handleSubmit}
                            validateOnChange
                            isInitialValid
                        >
                            {(props) => (
                                <Form onSubmit={props.handleSubmit}>
                                    <Typography sx={{fontWeight: 600}}>
                                        E-Mail
                                    </Typography>
                                    <TextField
                                        id='email'
                                        className={"input"}
                                        placeholder='Enter username'
                                        fullWidth
                                        required
                                        autoFocus
                                        onChange={props.handleChange}
                                        onBlur={props.handleBlur}
                                        value={props.values.email}
                                    />
                                    {props.errors.email && (
                                        <div id='feedback'>{props.errors.email}</div>
                                    )}
                                    <Typography sx={{fontWeight: 600}}>
                                        Password
                                    </Typography>
                                    <TextField
                                        id='password'
                                        className={"input"}
                                        placeholder='Enter password'
                                        type='password'
                                        fullWidth
                                        required
                                        onChange={props.handleChange}
                                        onBlur={props.handleBlur}
                                        value={props.values.password}
                                    />
                                    {props.errors.password && (
                                        <div id='feedback'>{props.errors.password}</div>
                                    )}

                                    <Button
                                        id='signIn'
                                        size={"large"}
                                        type='submit'
                                        className={"userButton"}
                                        color='primary'
                                        variant='contained'
                                        style={btnstyle}
                                        fullWidth
                                    >
                                        <Typography
                                            variant="body2"
                                            fontWeight={"bold"}
                                            className={"userButtonText"}
                                        >
                                            Sign in
                                        </Typography>
                                    </Button>
                                </Form>
                            )}
                        </Formik>
                        <Typography>
                            <Link href='#'>Forgot password ?</Link>
                        </Typography>
                        <Typography>
                            {' '}
                            Do you have an account ?<Link href='#'>Sign Up</Link>
                        </Typography>
                    </CardContent>
                </Card>
            </Grid>
        </Grid>
    );
};


export default Login;
