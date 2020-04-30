object calendar {

  final case class HourOfDay(value: Int) {
    def to(that: HourOfDay): Stream[HourOfDay] = (value to that.value).toStream.map(HourOfDay(_))

    def until(that: HourOfDay): Stream[HourOfDay] = (value until that.value).toStream.map(HourOfDay(_))
  }


  object HourOfDay {
    //    val min = HourOfDay(0)
    //    val max = HourOfDay(24)

    def min(a: HourOfDay, b: HourOfDay): HourOfDay = HourOfDay(Math.min(a.value, b.value))

    def max(a: HourOfDay, b: HourOfDay): HourOfDay = HourOfDay(Math.max(a.value, b.value))
  }

  sealed trait DayOfWeek

  object DayOfWeek {

    case object Sunday extends DayOfWeek

    case object Monday extends DayOfWeek

    case object Tuesday extends DayOfWeek

    case object Wednesday extends DayOfWeek

    case object Thursday extends DayOfWeek

    case object Friday extends DayOfWeek

    case object Saturday extends DayOfWeek

  }

  final case class TimeSpan(start: HourOfDay, end: HourOfDay) {
    def intersect(other: TimeSpan): Option[TimeSpan] =
      Some(TimeSpan(HourOfDay.max(start, other.start), HourOfDay.min(end, other.end)))
        .filter(ts => ts.start.value < ts.end.value)
  }

  sealed trait CalendarStatus

  object CalendarStatus {

    case object Busy extends CalendarStatus

    case object Free extends CalendarStatus

  }

  final case class CalendarRegion(span: TimeSpan, status: CalendarStatus) {
    def intersect(other: CalendarRegion): Option[CalendarRegion] = {
      span.intersect(other.span).filter(_ => status == other.status).map(span =>
        CalendarRegion(span, status)

      )
    }
  }

  final case class DailySchedule(set: Set[CalendarRegion]) {
    def isConflictFree: Boolean = set.foldLeft[(Boolean, Option[CalendarRegion])]((true, None)) {
      case ((true, Some(region)), current) => (region.intersect(current).isEmpty, Some(current))
      case ((true, None), current) => (true, Some(current))
      case _ => (false, None)
    }._1
  }

  final case class MonthlySchedule(daysOfMonth: Vector[DailySchedule])

  final case class Person(name: String)

  def findFreeTimes(lengthInHours: Int, friends: Map[Person, MonthlySchedule]): MonthlySchedule = ???

}