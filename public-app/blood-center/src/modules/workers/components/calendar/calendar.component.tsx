import {Calendar, momentLocalizer} from 'react-big-calendar'
import BigCalendar from 'react-big-calendar'
import moment from 'moment'


export const CalendarViewComponent = () => {
    const localizer = momentLocalizer(moment)

    return (
        <div>
           <Calendar
            localizer={localizer}
            events={[]}
            toolbar={true}
            views={{
                week: true,
                month: true,
                }}
            style={{ height: 500 }}
            />
        </div>
  )
}

export default CalendarViewComponent