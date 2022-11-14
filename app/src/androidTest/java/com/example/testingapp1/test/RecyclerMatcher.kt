package com.example.testingapp1.test

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {
    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null
            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (resources != null) {
                    idDescription = try {
                        resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        String.format("%s (resource name not found)",
                            *arrayOf<Any>(Integer.valueOf(recyclerViewId)))
                    }
                }
                description.appendText("with id: $idDescription")
            }

            override fun matchesSafely(view: View): Boolean {
                resources = view.getResources()
                if (childView == null) {
                    val recyclerView = view.getRootView().findViewById(
                        recyclerViewId) as RecyclerView
                    childView = if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        val viewHolder =
                            recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder == null) {
                            return false
                        }
                        viewHolder.itemView
                    } else {
                        return false
                    }
                }
                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView: View = childView!!.findViewById(targetViewId)
                    view === targetView
                }
            }
        }
    }
}

internal object TestUtils {
    fun <VH : RecyclerView.ViewHolder?> actionOnItemViewAtPosition(
        position: Int,
        @IdRes viewId: Int,
        viewAction: ViewAction,
    ): ViewAction {
        return ActionOnItemViewAtPositionViewAction<RecyclerView.ViewHolder?>(position,
            viewId,
            viewAction)
    }


    private class ActionOnItemViewAtPositionViewAction<VH : RecyclerView.ViewHolder?>(
        private val position: Int,
        @param:IdRes private val viewId: Int,
        private val viewAction: ViewAction,
    ) : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                ViewMatchers.isDisplayed())
        }

        override fun getDescription(): String {
            return ("actionOnItemAtPosition performing ViewAction: "
                    + viewAction.description
                    + " on item at position: "
                    + position)
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            ScrollToPositionViewAction(position).perform(uiController, view)
            uiController.loopMainThreadUntilIdle()
            val targetView = recyclerView.getChildAt(position).findViewById<View>(
                viewId)
            if (targetView == null) {
                throw PerformException.Builder().withActionDescription(this.toString())
                    .withViewDescription(
                        HumanReadables.describe(view))
                    .withCause(IllegalStateException(
                        "No view with id "
                                + viewId
                                + " found at position: "
                                + position))
                    .build()
            } else {
                viewAction.perform(uiController, targetView)
            }
        }
    }

    private class ScrollToPositionViewAction(private val position: Int) :
        ViewAction {
        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                ViewMatchers.isDisplayed())
        }

        override fun getDescription(): String {
            return "scroll RecyclerView to position: " + position
        }

        override fun perform(uiController: UiController?, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(position)
        }
    }
}

fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

