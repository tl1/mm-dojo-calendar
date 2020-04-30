object calendar {

  final case class HourOfDay(value: Int) {
    def to(that: HourOfDay): Stream[HourOfDay] = (value to that.value).toStream.map(HourOfDay(_))

    def until(that: HourOfDay): Stream[HourOfDay] = (value until that.value).toStream.map(HourOfDay(_))
  }
  object HourOfDay {
    val min = HourOfDay(0)
    val max = HourOfDay(24)
  }

  sealed trait DayOfWeek
  object DayOfWeek {
    case object Sunday    extends DayOfWeek
    case object Monday    extends DayOfWeek
    case object Tuesday   extends DayOfWeek
    case object Wednesday extends DayOfWeek
    case object Thursday  extends DayOfWeek
    case object Friday    extends DayOfWeek
    case object Saturday  extends DayOfWeek
  }

  final case class TimeSpan(start: HourOfDay, end: HourOfDay)

  sealed trait CalendarStatus
  object CalendarStatus {
    case object Busy extends CalendarStatus
    case object Free extends CalendarStatus
  }

  final case class CalendarRegion(span: TimeSpan, status: CalendarStatus) {
    def intersect(other: CalendarRegion): CalendarRegion = ???
  }

  final case class DailySchedule(set: Set[CalendarRegion])

  final case class MonthlySchedule(daysOfMonth: Vector[DailySchedule])

  final case class Person(name: String)

  def findFreeTimes(lengthInHours: Int, friends: Map[Person, MonthlySchedule]): MonthlySchedule = ???

}