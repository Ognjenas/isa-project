import RegistrationForm from "../modules/auth/components/registration-form"
import HomeView from "../modules/home"
import UpdateProfileView from "../modules/profiles/views/update-profile"


export const routes =
    [
        {
            path: "/",
            component: HomeView
        },
        {
            path: "/profile/update",
            component: UpdateProfileView
        }
        ,
        {
            path: "/registration",
            component: RegistrationForm
        }
    ]


export default routes