package com.example

import com.example.data.BillingManager
import com.example.data.CourseRepository
import com.example.model.InstrumentType
import com.example.model.UserProfile
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testAllInstrumentsHave90Lessons() {
        for (instrument in InstrumentType.entries) {
            val lessons = CourseRepository.getLessonsForInstrument(instrument)
            assertEquals("Instrument ${instrument.namePt} must have 90 lessons", 90, lessons.size)
            // Verify lessons are numbered 1 to 90
            for (i in 1..90) {
                val lesson = lessons[i - 1]
                assertEquals(i, lesson.lessonNumber)
                assertNotNull(lesson.title)
                assertTrue(lesson.didacticContent.isNotEmpty())
                assertTrue(lesson.practicalExercise.isNotEmpty())
            }
        }
    }

    @Test
    fun testEightModulesDefined() {
        val modules = CourseRepository.getModules()
        assertEquals(8, modules.size)
        assertEquals(1, modules.first().startLesson)
        assertEquals(90, modules.last().endLesson)
    }

    @Test
    fun testTwelveHourRefillCalculation() {
        val now = 1_000_000_000_000L
        val profileWith2Coins = UserProfile(
            coins = 2,
            lastRefillTimestamp = now - (6 * 3600 * 1000L) // 6 hours elapsed
        )
        val remaining = profileWith2Coins.calculateRemainingRefillMillis(now)
        assertEquals(6 * 3600 * 1000L, remaining)
        assertFalse(profileWith2Coins.isRefillReady(now))

        val profileExpired = UserProfile(
            coins = 1,
            lastRefillTimestamp = now - (13 * 3600 * 1000L) // 13 hours elapsed
        )
        assertTrue(profileExpired.isRefillReady(now))
        assertEquals(0L, profileExpired.calculateRemainingRefillMillis(now))
    }

    @Test
    fun testCoinPricingCalculation() {
        assertEquals(1.00, BillingManager.calculateCustomPrice(10), 0.001)
        assertEquals(5.00, BillingManager.calculateCustomPrice(50), 0.001)
        assertEquals(10.00, BillingManager.calculateCustomPrice(100), 0.001)
        assertEquals(2.50, BillingManager.calculateCustomPrice(25), 0.001)
    }
}
