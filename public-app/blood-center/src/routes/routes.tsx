import { RouteObject } from "react-router-dom";
import App from "../App";
import RegistrationForm from "../modules/auth/components/registration-form";
import ProfilesComponent from "../modules/profiles/profiles.component";
import UpdateProfileView from "../modules/profiles/views/update-profile";


export const routes: RouteObject[] = [

    {
        path: '/',
        element: <App />,
        children: [
            {
                path: 'profile',
                element: <ProfilesComponent />,
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