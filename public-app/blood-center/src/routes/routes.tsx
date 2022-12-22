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
                        element: <UpdateProfileView />,
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
                        element: <CenterRegistrationForm />,
                    },
                    {
                        path: "update/:cid",
                        element: <UpdateCenterView />,
                    },
                ],
            },
            {
                path: "users",
                element: <UsersComponent />,
                children: [
                    {
                        path: "",
                        element: <UsersView />,
                    },
                    {
                        path: "registration",
                        element: <RegistrationForm />,
                    },
                ],
            },
            {
                path: "survey",
                element: <MakeSurveyComponent />,
            },
            {
                path: "/worker/update/:wid",
                element: <UpdateWorkerView />,
            },
            {
                path: "worker/registration",
                element: <WorkerRegistrationForm />,
            },
            {
                path: "/login",
                element: <LoginForm />,
            },
            {
                path: "/appointments",
                element: <ShowAppointmentsView/>
            }
        ],
    },
]

export default routes
