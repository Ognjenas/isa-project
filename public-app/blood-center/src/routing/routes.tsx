import React from "react"
import { RouteObject } from "react-router-dom"
import App from "../App"
import RegistrationForm from "../modules/auth/components/registration-form"
import UpdateProfileView from "../modules/profiles/views/update-profile"
import ProfilesView from "../modules/profiles/profiles.view"


export const routes: RouteObject[] = [
    {
        path: '/',
        element: <App />,
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