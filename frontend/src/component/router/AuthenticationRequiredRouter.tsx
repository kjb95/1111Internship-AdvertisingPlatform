import React from 'react';
import {Navigate, Outlet} from "react-router-dom";
import {JWT_TOKEN} from "../../constants/Constant";

function AuthenticationRequiredRouter() {
    const isAuthenticated = sessionStorage.getItem(JWT_TOKEN);

    return (isAuthenticated) ? <Outlet/> : <Navigate to="/login"/>;
}

export default AuthenticationRequiredRouter;