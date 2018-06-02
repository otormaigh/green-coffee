package com.mauriciotogneri.greencoffee.interactions

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher

class ActionableData(private val dataInteraction: DataInteraction) : ActionableObject() {

  override fun check(viewAssertion: ViewAssertion): ActionableObject {
    return ActionableView(dataInteraction.check(viewAssertion))
  }

  override fun perform(viewAction: ViewAction): ActionableObject {
    return ActionableView(dataInteraction.perform(viewAction))
  }

  override fun text(): String {
    val stringHolder = arrayOf<String>()

    dataInteraction.perform(object : ViewAction {
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