package ro.sorin.blanknote.model.converter

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
            Calendar.getInstance().apply { timeInMillis = value }
}