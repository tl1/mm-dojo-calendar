import org.scalatest.{FlatSpec, Matchers}
import calendar._

class CalendarSpec extends FlatSpec with Matchers {
  "CalenderRegion" should "have intersection if overlapping with same status" in {
    val a = CalendarRegion(TimeSpan(HourOfDay(1), HourOfDay(3)), CalendarStatus.Busy)
    val b = CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(4)), CalendarStatus.Busy)
    a.intersect(b) shouldBe CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(3)), CalendarStatus.Busy)
  }
}
