package com.mauriciotogneri.greencoffee.interactions

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.ImageView

import org.hamcrest.Description
import org.hamcrest.Matchers

abstract class ActionableObject {
  val isEmpty: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.withText("")))

  val isNotEmpty: ActionableObject
    get() = check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

  val isChecked: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isChecked()))

  val isNotChecked: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isNotChecked()))

  val isClickable: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isClickable()))

  val isFocusable: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isFocusable()))

  val isNotFocusable: ActionableObject
    get() = check(ViewAssertions.matches(Matchers.not(ViewMatchers.isFocusable())))

  val isEnabled: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isEnabled()))

  val isDisabled: ActionableObject
    get() = check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())))

  val isSelected: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isSelected()))

  val isNotSelected: ActionableObject
    get() = check(ViewAssertions.matches(Matchers.not(ViewMatchers.isSelected())))

  val isDisplayed: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

  val isCompletelyDisplayed: ActionableObject
    get() = check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

  val isNotDisplayed: ActionableObject
    get() = check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

  fun click(): ActionableObject {
    return perform(ViewActions.click())
  }

  fun doubleClick(): ActionableObject {
    return perform(ViewActions.doubleClick())
  }

  fun longClick(): ActionableObject {
    return perform(ViewActions.longClick())
  }

  fun type(text: String): ActionableObject {
    return perform(ViewActions.typeText(text))
  }

  fun clearText(): ActionableObject {
    return perform(ViewActions.clearText())
  }

  fun scrollTo(): ActionableObject {
    return perform(ViewActions.scrollTo())
  }

  fun swipeUp(): ActionableObject {
    return perform(ViewActions.swipeUp())
  }

  fun swipeDown(): ActionableObject {
    return perform(ViewActions.swipeDown())
  }

  fun swipeLeft(): ActionableObject {
    return perform(ViewActions.swipeLeft())
  }

  fun swipeRight(): ActionableObject {
    return perform(ViewActions.swipeRight())
  }

  fun doesNotExist(): ActionableObject {
    return check(ViewAssertions.doesNotExist())
  }

  fun checkIfDoesNotExist(): Boolean {
    return checkIf(Runnable { this.doesNotExist() })
  }

  fun contains(text: Any): ActionableObject {
    return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString(text.toString()))))
  }

  fun checkIfContains(text: Any): Boolean {
    return checkIf(Runnable { contains(text) })
  }

  fun doesNotContain(text: Any): ActionableObject {
    return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.not(Matchers.containsString(text.toString())))))
  }

  fun checkIfDoesNotContain(text: Any): Boolean {
    return checkIf(Runnable { doesNotContain(text) })
  }

  fun checkIfIsEmpty(): Boolean {
    return checkIf(Runnable { isEmpty })
  }

  fun checkIfIsNotEmpty(): Boolean {
    return checkIf(Runnable { isNotEmpty })
  }

  fun hasFocus(): ActionableObject {
    return check(ViewAssertions.matches(ViewMatchers.hasFocus()))
  }

  fun checkIfHasFocus(): Boolean {
    return checkIf(Runnable { this.hasFocus() })
  }

  fun doesNotHaveFocus(): ActionableObject {
    return check(ViewAssertions.matches(Matchers.not(ViewMatchers.hasFocus())))
  }

  fun checkIfDoesNotHaveFocus(): Boolean {
    return checkIf(Runnable { this.doesNotHaveFocus() })
  }

  fun hasErrorText(text: Any): ActionableObject {
    return check(ViewAssertions.matches(ViewMatchers.hasErrorText(text.toString())))
  }

  fun checkIfHasErrorText(text: Any): Boolean {
    return checkIf(Runnable { hasErrorText(text) })
  }

  fun checkIfIsChecked(): Boolean {
    return checkIf(Runnable { isChecked })
  }

  fun checkIfIsNotChecked(): Boolean {
    return checkIf(Runnable { isNotChecked })
  }

  fun checkIfIsClickable(): Boolean {
    return checkIf(Runnable { isClickable })
  }

  fun checkIfIsFocusable(): Boolean {
    return checkIf(Runnable { isFocusable })
  }

  fun checkIfIsNotFocusable(): Boolean {
    return checkIf(Runnable { isNotFocusable })
  }

  fun checkIfIsEnabled(): Boolean {
    return checkIf(Runnable { isEnabled })
  }

  fun checkIfIsDisabled(): Boolean {
    return checkIf(Runnable { isDisabled })
  }

  fun checkIfIsSelected(): Boolean {
    return checkIf(Runnable { isSelected })
  }

  fun checkIfIsNotSelected(): Boolean {
    return checkIf(Runnable { isNotSelected })
  }

  fun checkIfIsDisplayed(): Boolean {
    return checkIf(Runnable { isDisplayed })
  }

  fun checkIfIsCompletelyDisplayed(): Boolean {
    return checkIf(Runnable { isCompletelyDisplayed })
  }

  fun checkIfIsNotDisplayed(): Boolean {
    return checkIf(Runnable { isNotDisplayed })
  }

  fun hasDrawable(): ActionableObject {
    return check(ViewAssertions.matches(hasDrawableImageView()))
  }

  fun checkIfHasDrawable(): Boolean {
    return checkIf(Runnable { this.hasDrawable() })
  }

  fun doesNotHaveDrawable(): ActionableObject {
    return check(ViewAssertions.matches(Matchers.not(hasDrawableImageView())))
  }

  fun checkIfDoesNotHaveDrawable(): Boolean {
    return checkIf(Runnable { this.doesNotHaveDrawable() })
  }

  abstract fun check(viewAssertion: ViewAssertion): ActionableObject

  abstract fun perform(viewAction: ViewAction): ActionableObject

  abstract fun text(): String

  private fun checkIf(runnable: Runnable): Boolean {
    return try {
      runnable.run()
      true
    } catch (e: Exception) {
      false
    }
  }

  fun hasDrawableImageView(): BoundedMatcher<View, ImageView> {
    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
      override fun describeTo(description: Description) {
        description.appendText("has drawable")
      }

      public override fun matchesSafely(imageView: ImageView): Boolean {
        return imageView.drawable != null
      }
    }
  }
}