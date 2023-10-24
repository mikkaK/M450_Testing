import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import {useEffect, useState} from 'react';
import {UserProfile} from '../../../types/models/UserProfile.model';
import UserProfileService from '../../../Services/UserProfileService';
import {NavLink, useNavigate} from 'react-router-dom';
import {Grid, MenuItem, Paper, Select, Typography} from "@mui/material";
import "../../../style/userprofile.css"

const UserProfileTable = () => {
    const [sortBy, setSortBy] = useState<string>("age");
    const navigate = useNavigate();
    const [userProfiles, setUserProfiles] = useState<UserProfile[]>([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [asc, setAsc] = useState(true);

    // define pageSize for pagination
    const pageSize = 4;

    const sortByOptions = [
        {label: 'Age', value: 'age'},
        {label: 'Address', value: 'address'},
        {label: 'Profile Picture URL', value: 'profilePictureURL'},
        {label: 'Birth Date', value: 'birthDate'},
    ];

    const ascOrDesc = [
        {label: 'Descending', value: "false"},
        {label: 'Ascending', value: "true"},
    ];

    const handleAscDesc = () => {
        setAsc(current => !current)
    }

    useEffect(() => {
        UserProfileService.getAllUserProfiles(pageSize, currentPage, sortBy, asc).then((data) => {
            setUserProfiles(data.data);
        });
    }, [currentPage, sortBy, asc]);

    // To handle pagination

    const handlePrevClick = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handleNextClick = () => {
        if (userProfiles.length === pageSize) {
            setCurrentPage(currentPage + 1);
        }
    };

    // CRUD Functions

    const handleAdd = () => {
        navigate('../userprofileedit/');
    };

    const handleEdit = (id: string) => {
        navigate('../userprofileedit/' + id);
    };

    const handleDelete = (id: string) => {
        UserProfileService.deleteUserProfile(id);
    };

    return (
        <>
            <Grid container columns={{xs: 6, sm: 9, md: 12}} p={2}>
                <Grid item xs={3} sm={4.5} md={6} p={1}>
                    <Typography sx={{fontWeight: 600}} className="typography">
                        Sort by
                    </Typography>
                    <Select
                        name={"sortBy"}
                        value={sortBy}
                        label={"Sort By"}
                        onChange={(e) => setSortBy(e.target.value)}
                        fullWidth
                        sx={{
                            color: "white !important",
                            '.MuiInput-input': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '&:hover .MuiOutlinedInput-notchedOutline': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '.MuiSvgIcon-root ': {
                                fill: "white !important",
                            }
                        }}
                    >
                        {sortByOptions.map((opt) => (
                            <MenuItem key={opt.value} value={opt.value}>
                                {opt.label}
                            </MenuItem>
                        ))}
                    </Select>
                </Grid>
                <Grid item xs={3} sm={4.5} md={6} p={1}>
                    <Typography sx={{fontWeight: 600}} className="typography">
                        Ascending or Descending
                    </Typography>
                    <Select
                        name={"ascOrDesc"}
                        value={asc}
                        label={"Ascending or Descending"}
                        onChange={handleAscDesc}
                        fullWidth
                        sx={{
                            color: "white !important",
                            '.MuiInput-input': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '&:hover .MuiOutlinedInput-notchedOutline': {
                                color: "white !important",
                                borderColor: 'rgba(228, 219, 233, 0.25)',
                            },
                            '.MuiSvgIcon-root ': {
                                fill: "white !important",
                            }
                        }}
                    >
                        {ascOrDesc.map((opt) => (
                            <MenuItem key={opt.value} value={opt.value}>
                                {opt.label}
                            </MenuItem>
                        ))}
                    </Select>
                </Grid>
                <Grid item xs={6} sm={9} md={12} p={1}>
                    <Typography sx={{fontWeight: 600}} className="typography">
                        Page {currentPage + 1} of {userProfiles.length === pageSize ? currentPage + 2 : currentPage + 1}
                    </Typography>
                </Grid>
                {userProfiles.map((userProfile) => (
                    <Grid item xs={3} p={1} key={userProfile.id}>
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
                                <CardActions>
                                    <Button
                                        className={"userButton"}
                                        size='small'
                                        color='primary'
                                        variant='contained'
                                        onClick={() => handleEdit(userProfile.user!.id)}
                                    >
                                        <Typography
                                            variant="body2"
                                            fontWeight={"bold"}
                                            className={"userButtonText"}
                                        >
                                            Edit
                                        </Typography>
                                    </Button>
                                    <Button
                                        className={"userButton"}
                                        size='small'
                                        color='error'
                                        variant='contained'
                                        onClick={() => handleDelete(userProfile.user!.id)}
                                    >
                                        <Typography
                                            variant="body2"
                                            fontWeight={"bold"}
                                            className={"userButtonText"}
                                        >
                                            Delete
                                        </Typography>
                                    </Button>
                                </CardActions>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
            <Grid container>
                <Grid item xs={4}/>
                <Grid item xs={1.5}
                      display="flex"
                      justifyContent="center"
                      alignItems="center">
                    <Button
                        className={"userButton"}
                        color='secondary'
                        variant='contained'
                        onClick={handlePrevClick}
                        sx={{mx: .5}}
                    >
                        <Typography
                            variant="body2"
                            fontWeight={"bold"}
                            className={"userButtonText"}
                        >
                            Previous
                        </Typography>
                    </Button>
                    <Button
                        className={"userButton"}
                        color='secondary'
                        variant='contained'
                        onClick={handleNextClick}
                        sx={{mx: .5}}
                    >
                        <Typography
                            variant="body2"
                            fontWeight={"bold"}
                            className={"userButtonText"}
                        >
                            Next
                        </Typography>
                    </Button>
                </Grid>
                <Grid item xs={1}
                      display="flex"
                      justifyContent="center"
                      alignItems="center">
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
                            Add
                        </Typography>
                    </Button>
                </Grid>
                <Grid item xs={1.5}
                      display="flex"
                      justifyContent="center"
                      alignItems="center">
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
                    <Button
                        className={"userButton"}
                        color='info'
                        variant='contained'
                        sx={{mx: .5}}
                    >
                        <NavLink to={"/authHomeAdmin"} className={"userButtonText"}>
                            <Typography
                                variant="body2"
                                fontWeight={"bold"}
                                className={"userButtonText"}
                            >
                                Back
                            </Typography>
                        </NavLink>
                    </Button>
                </Grid>
                <Grid item xs={4}/>
            </Grid>
        </>
    );
};

export default UserProfileTable;
