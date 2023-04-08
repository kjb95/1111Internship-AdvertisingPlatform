import {Outlet} from "react-router-dom";
import SimplePage from "../../pages/SimplePage";

interface RoleRequiredRouterProps {
    role: boolean | undefined;
}

function RoleRequiredRouter({role}: RoleRequiredRouterProps) {
    return role ? <Outlet/> : <SimplePage title="401 Unauthorized"/>;
}

export default RoleRequiredRouter;
