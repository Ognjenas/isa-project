import {Calendar, momentLocalizer} from 'react-big-calendar'
import BigCalendar from 'react-big-calendar'
import moment from 'moment'
import { workerService } from "../../services/worker.service"
import { AppointmentToShow } from '../../dtos/appointment-show.dto'
import { useEffect, useState } from 'react'
import "react-big-calendar/lib/css/react-big-calendar.css";
import { Flex } from '@chakra-ui/layout'


export const CalendarViewComponent = () => {
    const localizer = momentLocalizer(moment)
    const [events, setEvents] = useState<AppointmentToShow[]>([])

    const handleOnMounted = async () => {
        let events: AppointmentToShow[] = await workerService.getEvents()
        setEvents(events)
    }

    useEffect(() => {
        handleOnMounted()
    }, [])


    return (
        <Flex 
        justify-content= {'center'}
        align-items= {'center'}
            >
           <Calendar
            localizer={localizer}
            events={events}
            startAccessor={(event) => { return new Date(event.start) }}
            endAccessor={(event) => { return new Date(event.end) }}
            toolbar={true}
            views={{
                week: true,
                month: true,
                }}
            style={{ height: 900, width: 1200}}
            />
        </Flex>
  )
}

export default CalendarViewComponent