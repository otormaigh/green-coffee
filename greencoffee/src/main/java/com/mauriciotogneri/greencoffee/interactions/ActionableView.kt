package com.mauriciotogneri.greencoffee.interactions

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher

class ActionableView(private val viewInteraction: ViewInteraction) : ActionableObject() {

  override fun check(viewAssertion: ViewAssertion): ActionableObject {
    return ActionableView(viewInteraction.check(viewAssertion))
  }

  override fun perform(viewAction: ViewAction): ActionableObject {
    return ActionableView(viewInteraction.perform(viewAction))
  }

  override fun text(): String {
    val stringHolder = arrayOf<String>()

    viewInteraction.perform(object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(TextView::class.java)
      }

      override fun getDescription(): String {
        return "getting text from a TextView"
      }

      override fun perform(uiController: UiController, view: View) {
        val textView = view as TextView
        stringHolder[0] = textView.text.toString()
      }
    })

    return stringHolder[0]
  }
}