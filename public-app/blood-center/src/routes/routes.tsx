import { RouteObject } from "react-router-dom";
import App from "../App";
import RegistrationForm from "../modules/auth/components/registration-form";
import ProfilesView from "../modules/profiles/profiles.view";
import UpdateProfileView from "../modules/profiles/views/update-profile";
import ErrorComponent from "../modules/shared/components/error";


export const routes: RouteObject[] = [

    {
        path: '/',
        element: <App />,
        errorElement: <ErrorComponent />,
        children: [
            {
                path: 'profile',
                element: <ProfilesView />,
                children: [
                    {
                        path: 'update',
                        element: <UpdateProfileView />
                    }
                ]
            },
            {
                path: 'registration',
                element: <RegistrationForm />
            }
        ]
    }

]

export default routes