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
import ShowCentersComponent from "../modules/center/components/show-centers"
import { ProtectedWrapper } from "../util/protected-wrapper"
import path from "path"
import FreeAppointments from "../modules/appointments/components/free-appointments"
import MyAppointments from "../modules/appointments/components/my-appointments"
import AdminRegistrationForm from "../modules/system_admins/components/register-new-system-admin"
import AdminChangePasswordForm from "../modules/system_admins/components/change-password"
import CalendarViewComponent from "../modules/workers/components/calendar"
import CheckFirstLoginAdmin from "../modules/system_admins/components/check-first-loggin/check-first-loggin.component"

import CreateAppointmentView from "../modules/appointments/views/create-appointment.view"
import AppointmentsComponent from "../modules/appointments/appointments.component"

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
                        element: (
                            <ProtectedWrapper
                                roles={["REGULAR", "WORKER", "ADMINISTRATOR"]}
                            >
                                <CheckFirstLoginAdmin>
                                    <UpdateProfileView />
                                </CheckFirstLoginAdmin>
                            </ProtectedWrapper>
                        ),
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
                        element: (
                            <ProtectedWrapper roles={["ADMINISTRATOR"]}>
                                <CheckFirstLoginAdmin>
                                    <CenterRegistrationForm />
                                </CheckFirstLoginAdmin>
                            </ProtectedWrapper>
                        ),
                    },
                    {
                        path: "update/:cid",
                        element: (
                            <ProtectedWrapper
                                roles={["WORKER", "ADMINISTRATOR"]}
                            >
                                <CheckFirstLoginAdmin>
                                    <UpdateCenterView />
                                </CheckFirstLoginAdmin>
                            </ProtectedWrapper>
                        ),
                    },
                    {
                        path: ":cid/free-appointments",
                        element: <FreeAppointments />,
                    },
                ],
            },
            {
                path: "users",
                element: <UsersComponent />,
                children: [
                    {
                        path: "",
                        element: (
                            <ProtectedWrapper
                                roles={["WORKER", "ADMINISTRATOR"]}
                            >
                                <CheckFirstLoginAdmin>
                                    <UsersView />
                                </CheckFirstLoginAdmin>
                            </ProtectedWrapper>
                        ),
                    },
                    {
                        path: "registration",
                        element: <RegistrationForm />,
                    },
                ],
            },
            {
                path: "survey",
                element: (
                    <ProtectedWrapper roles={["REGULAR"]}>
                        <CheckFirstLoginAdmin>
                            <MakeSurveyComponent />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>
                ),
            },
            {
                path: "/worker/update/:wid",
                element: (
                    <ProtectedWrapper roles={["ADMINISTRATOR"]}>
                        <CheckFirstLoginAdmin>
                            <UpdateWorkerView />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>
                ),
            },
            {
                path: "worker/registration",
                element: (
                    <ProtectedWrapper roles={["WORKER", "ADMINISTRATOR"]}>
                        <CheckFirstLoginAdmin>
                            <WorkerRegistrationForm />
                        </CheckFirstLoginAdmin>  
                    </ProtectedWrapper>
                ),
            },
            {
                path: "/login",
                element: <LoginForm />,
            },
            {
                path: "/appointments",
                element: (
                    <ProtectedWrapper roles={["WORKER", "ADMINISTRATOR"]}>
                        <CheckFirstLoginAdmin>
                            <AppointmentsComponent />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>
                ),
                children: [
                    {
                        path: '',
                        element: <ShowAppointmentsView/>
                    },
                    {
                        path: 'create',
                        element: (
                            <ProtectedWrapper roles={["ADMINISTRATOR"]}>
                                <CreateAppointmentView/>
                            </ProtectedWrapper>
                        )
                    }
                ]
            },
            {

                path: "/my-appointments",
                element: (
                    <ProtectedWrapper roles={["REGULAR"]}>
                        <CheckFirstLoginAdmin>
                            <MyAppointments />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>
                )
            },
            {
                path: "admin/registration",
                element:(
                    <ProtectedWrapper roles={["ADMINISTRATOR"]}>
                        <CheckFirstLoginAdmin>
                            <AdminRegistrationForm />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>),
            },
            {
                path: "password-change",
                element: <AdminChangePasswordForm />,
            },
            {
                path: "calendar-view",
                element: (
                    <ProtectedWrapper roles={["WORKER"]}>
                        <CheckFirstLoginAdmin>
                            <CalendarViewComponent />
                        </CheckFirstLoginAdmin>
                    </ProtectedWrapper>),
            },
        ],
    },
]

export default routes
