package com.mauriciotogneri.greencoffee.interactions

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.Matcher

class DataMatcher<T, C>(
    @param:IdRes private val resourceId: Int = 0,
    private val clazz: Class<T>) {

  fun with(content: C): ActionableData {
    val dataInteraction = onData(dataMatcher(content))

    return if (resourceId != 0) {
      ActionableData(dataInteraction.inAdapterView(withId(resourceId)))
    } else {
      ActionableData(dataInteraction)
    }
  }

  private fun dataMatcher(content: C): Matcher<Any> {
    val dataMatcher = this

    return object : BoundedMatcher<Any, T>(clazz) {
      public override fun matchesSafely(data: T): Boolean {
        return dataMatcher.matches(data, content)
      }

      override fun describeTo(description: Description) {
        description.appendText(String.format("with content: '%s'", content))
      }
    }
  }

  fun matches(element: T, content: C): Boolean {
    return element == content
  }
}