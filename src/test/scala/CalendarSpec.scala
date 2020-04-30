import calendar._
import org.scalatest.{FlatSpec, Matchers}

class CalendarSpec extends FlatSpec with Matchers {
  "CalenderRegion" should "have intersection if overlapping with same status" in {
    val a = CalendarRegion(TimeSpan(HourOfDay(1), HourOfDay(3)), CalendarStatus.Busy)
    val b = CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(4)), CalendarStatus.Busy)
    a.intersect(b) should contain (CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(3)), CalendarStatus.Busy))
  }

  it should "have intersection if overlapping with different status" in {
    val a = CalendarRegion(TimeSpan(HourOfDay(1), HourOfDay(3)), CalendarStatus.Busy)
    val b = CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(4)), CalendarStatus.Free)
    a.intersect(b) shouldBe None
  }

  "DailySchedule" should "be able to detect conflicts if they exist" in {
    val a = CalendarRegion(TimeSpan(HourOfDay(1), HourOfDay(3)), CalendarStatus.Busy)
    val b = CalendarRegion(TimeSpan(HourOfDay(2), HourOfDay(4)), CalendarStatus.Busy)
    DailySchedule(Set(a, b)).isConflictFree shouldBe false
  }

  it should "be able to detect no conflicts" in {
    val a = CalendarRegion(TimeSpan(HourOfDay(1), HourOfDay(3)), CalendarStatus.Busy)
    val c = CalendarRegion(TimeSpan(HourOfDay(5), HourOfDay(6)), CalendarStatus.Busy)
    DailySchedule(Set(a, c)).isConflictFree shouldBe true
  }
}
