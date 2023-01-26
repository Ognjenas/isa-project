import { Calendar, momentLocalizer } from 'react-big-calendar'
import BigCalendar from 'react-big-calendar'
import moment from 'moment'
import { workerService } from "../../services/worker.service"
import { AppointmentToShow } from '../../dtos/appointment-show.dto'
import { useEffect, useState } from 'react'
import "react-big-calendar/lib/css/react-big-calendar.css";
import { Flex } from '@chakra-ui/layout'
import { useNavigate } from "react-router-dom"
import OpenAppointmentComponent from '../open-appointment'


export const CalendarViewComponent = () => {
    const localizer = momentLocalizer(moment)
    const [events, setEvents] = useState<AppointmentToShow[]>([])
    const navigate = useNavigate()

    const handleOnMounted = async () => {
        let events: AppointmentToShow[] = await workerService.getEvents()
        setEvents(events)
    }

    useEffect(() => {
        handleOnMounted()
    }, []);


    return (
        <Flex
            flexDirection="column"
            height="100%"
            width="100%"
            justifyContent="flex-start"
            alignItems="center"
            gap={10}
        ><OpenAppointmentComponent />
            <Calendar
                onSelectEvent={event => navigate("/blood-donation/" + event.id)}
                localizer={localizer}
                events={events}
                startAccessor={(event) => { return new Date(event.start) }}
                endAccessor={(event) => { return new Date(event.end) }}
                toolbar={true}
                views={{
                    week: true,
                    month: true,
                }}
                style={{ height: '90%', width: '90%' }}
            />
        </Flex>
    )
}

export default CalendarViewComponent