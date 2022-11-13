import { RouteObject } from "react-router-dom"
import App from "../App"
import RegistrationForm from "../modules/auth/components/registration-form"
import ShowCentersComponent from "../modules/home/components/show-centers-component"
import ProfilesView from "../modules/profiles/profiles.view"
import UpdateProfileView from "../modules/profiles/views/update-profile"
import ErrorComponent from "../modules/shared/components/error"
import MakeSurveyComponent from "../modules/surveys/components/make-survey.component"
import UpdateWorkerView from "../modules/worker/views/worker-update";
import UpdateCenterView from "../modules/center/views/center-update";
import CenterRegistrationForm from "../modules/centers/components/center-registration-form";
import UsersView from "../modules/users/components";


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
                path: "registration",
                element: <RegistrationForm />,
            },
            {
                path: "centers",
                element: <ShowCentersComponent />,
            },
            {
                path: "survey",
                element: <MakeSurveyComponent />,
            },
            {
                path:"/worker/update/:wid",
                element: <UpdateWorkerView/>
            },
            {
                path:"/center/update/:cid",
                element: <UpdateCenterView/>
            },
            {   

                path: 'registration',
                element: <RegistrationForm />
            },
            {
                path: 'centers/registration',
                element: <CenterRegistrationForm />
            },
            {
                path: 'usersview',
                element: <UsersView />
            }
        ]
    }

]

export default routes
