import { RouteObject } from "react-router-dom"
import App from "../App"
import RegistrationForm from "../modules/auth/components/registration-form"
import ProfilesView from "../modules/profiles/profiles.view"
import UpdateProfileView from "../modules/profiles/views/update-profile"
import ErrorComponent from "../modules/shared/components/error"
import MakeSurveyComponent from "../modules/surveys/components/make-survey.component"
import UpdateWorkerView from "../modules/workers/views/worker-update"
import UpdateCenterView from "../modules/center/views/center-update"
import CenterRegistrationForm from "../modules/center/components/center-registration-form"
import UsersView from "../modules/users/components"
import WorkerRegistrationForm from "../modules/workers/components/worker-registration/worker-registration.component"
import CenterComponent from "../modules/center/centers.component"
import { UsersComponent } from "../modules/users/users.component"
import LoginForm from "../modules/auth/components/login-form"
import ShowAppointmentsView from "../modules/appointments/views"
import ShowCentersComponent from "../modules/center/components/show-centers";
import {ProtectedWrapper} from "../util/protected-wrapper";

export const routes: RouteObject[] = [
    {
        path: "/",
        element: <App />,
        errorElement: <ErrorComponent />,
        children: [
            {
                path: "profile",
                element: <ProfilesView />,
                children: [
                    {
                        path: "update",
                        element:<ProtectedWrapper roles={["REGULAR","WORKER","ADMINISTRATOR"]}><UpdateProfileView/></ProtectedWrapper>,
                    },
                ],
            },
            {
                path: "centers",
                element: <CenterComponent />,
                children: [
                    {
                        path: "",
                        element: <ShowCentersComponent />,
                    },
                    {
                        path: "registration",
                        element:<ProtectedWrapper roles={["ADMINISTRATOR"]}><><CenterRegistrationForm/></></ProtectedWrapper>,
                    },
                    {
                        path: "update/:cid",
                        element:<ProtectedWrapper roles={["WORKER","ADMINISTRATOR"]}><><UpdateCenterView/></></ProtectedWrapper>,
                    },
                ],
            },
            {
                path: "users",
                element: <UsersComponent />,
                children: [
                    {
                        path: "",
                        element:<ProtectedWrapper roles={["WORKER","ADMINISTRATOR"]}><><UsersView /></></ProtectedWrapper>,
                    },
                    {
                        path: "registration",
                        element: <RegistrationForm />,
                    },
                ],
            },
            {
                path: "survey",
                element: <ProtectedWrapper roles={["REGULAR"]}><MakeSurveyComponent /></ProtectedWrapper>,
            },
            {
                path: "/worker/update/:wid",
                element: <ProtectedWrapper roles={["ADMINISTRATOR"]}><UpdateWorkerView/></ProtectedWrapper>,
            },
            {
                path: "worker/registration",
                element: <ProtectedWrapper roles={["WORKER","ADMINISTRATOR"]}><WorkerRegistrationForm /></ProtectedWrapper>,
            },
            {
                path: "/login",
                element: <LoginForm />,
            },
            {
                path: "/appointments",
                element:<ProtectedWrapper roles={["WORKER","ADMINISTRATOR"]}><ShowAppointmentsView/></ProtectedWrapper>
            }
        ],
    },
]

export default routes
