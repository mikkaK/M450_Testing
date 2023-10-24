import {Route, Routes} from "react-router-dom";
import LoginPage from "../components/pages/LoginPage/LoginPage";
import PrivateRoute from "./PrivateRoute";
import HomePage from "../components/pages/HomePage";
import UserTable from "../components/pages/UserPage/UserTable";
import UserPage from "../components/pages/UserPage/UserPage";
import UserProfileTable from "../components/pages/UserProfilePage/UserProfileTable";
import UserProfilePage from "../components/pages/UserProfilePage/UserProfilePage";
import HomePageLoggedInUser from "../components/pages/HomePageLoggedInUser";
import authorities from "../config/Authorities";
import HomePageLoggedInAdmin from "../components/pages/HomePageLoggedInAdmin";
import Unauthorized from "../components/pages/Unauthorized";

/**
 * Router component renders a route switch with all available pages
 */

const Router = () => {

    /** navigate to different "home"-locations depending on Role the user have */

    return (
        <Routes>
            <Route path={"/"} element={<HomePage/>}/>
            <Route path={"/login"} element={<LoginPage/>}/>
            <Route path={"/authHomeUser"} element={<PrivateRoute authorities={[{
                id: "",
                name: authorities.DEFAULT
            }]} element={<HomePageLoggedInUser/>}/>}/>
            <Route path={"/authHomeAdmin"} element={<PrivateRoute authorities={[{
                id: "",
                name: authorities.USER_MODIFY
            }]} element={<HomePageLoggedInAdmin/>}/>}
            />

            <Route
                path={"/users"}
                element={<PrivateRoute authorities={[{
                    id: "",
                    name: authorities.DEFAULT
                }]} element={<UserTable/>}/>}
            />
            <Route
                path="/useredit"
                element={
                    <PrivateRoute authorities={[]} element={<PrivateRoute authorities={[{
                        id: "",
                        name: authorities.DEFAULT
                    }]} element={<UserPage/>}/>}/>
                }
            />
            <Route
                path="/useredit/:userId"
                element={<PrivateRoute authorities={[{
                    id: "",
                    name: authorities.DEFAULT
                }]} element={<UserPage/>}/>}
            />

            <Route
                path={"/userprofile"}
                element={<PrivateRoute authorities={[{
                    id: "",
                    name: authorities.USER_MODIFY
                }]} element={<UserProfileTable/>}/>}
            />
            <Route
                path="/userprofileedit"
                element={<PrivateRoute authorities={[{
                    id: "",
                    name: authorities.DEFAULT
                }]} element={<UserProfilePage/>}/>}
            />
            <Route
                path="/userprofileedit/:userProfileId"
                element={<PrivateRoute authorities={[{
                    id: "",
                    name: authorities.DEFAULT
                }]} element={<UserProfilePage/>}/>}
            />

            <Route
                path="/unauthorized"
                element={
                    <PrivateRoute authorities={[]} element={<Unauthorized/>}></PrivateRoute>
                }
            />

            <Route path="*" element={<div>Not Found</div>}/>
        </Routes>
    );
};

export default Router;
