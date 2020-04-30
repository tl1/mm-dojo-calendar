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

  /**
   * EXERCISE 1
   *
   * Explore the structure of `CalendarStatus` by deciding what composable,
   * orthogonal operations to add to the data type.
   */
  sealed trait CalendarStatus
  object CalendarStatus {
    case object Busy extends CalendarStatus
    case object Free extends CalendarStatus
  }

  /**
   * EXERCISE 2
   *
   * Explore the structure of `CalendarRegion` by deciding what composable,
   * orthogonal operations to add to the data type.
   */
  final case class CalendarRegion(span: TimeSpan, status: CalendarStatus)

  /**
   * EXERCISE 3
   *
   * Explore the structure of `DailySchedule` by deciding what composable,
   * orthogonal operations to add to the data type.
   *
   * HINT: Consider the union and intersection of two daily schedules.
   */
  final case class DailySchedule(set: Set[CalendarRegion])

  /**
   * EXERCISE 4
   *
   * Explore the structure of `MonthlySchedule` by deciding what composable,
   * orthogonal operations to add to the data type.
   */
  final case class MonthlySchedule(daysOfMonth: Vector[DailySchedule])

  final case class Person(name: String)

  /**
   * EXERCISE 5
   *
   * Using the operators you build, express a solution to the following
   * problem: find all the free times that a group of friends can virtually
   * meet for the specified number of hours.
   */
  def findFreeTimes(lengthInHours: Int, friends: Map[Person, MonthlySchedule]): MonthlySchedule = ???

}