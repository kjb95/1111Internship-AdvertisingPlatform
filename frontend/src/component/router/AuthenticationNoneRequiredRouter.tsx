import React from "react";
import {JWT_TOKEN} from "../../constants/Constant";
import {Navigate, Outlet} from "react-router-dom";

function AuthenticationNoneRequiredRouter() {
    const isAuthenticated = sessionStorage.getItem(JWT_TOKEN);

    return isAuthenticated ? <Navigate replace to="/"/> : <Outlet/>;
}

export default AuthenticationNoneRequiredRouter;
