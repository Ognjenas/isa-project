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
    ]


export default routes