package com.avito.android.test.checks

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.v7.widget.RecyclerView
import android.view.View
import com.avito.android.test.waitToPerform
import com.forkingcode.espresso.contrib.DescendantViewActions
import org.hamcrest.Matcher

@Deprecated("todo use InteractionContextMatcherChecksDriver")
class OnDescendantMatcherListItemChecksDriver(
    private val listMatcher: Matcher<View>,
    private val matcher: Matcher<View>,
    private val childMatcher: Matcher<View>
) : ChecksDriver {

    private val interaction: ViewInteraction
        get() = Espresso.onView(listMatcher)

    override fun check(assertion: ViewAssertion) {
        interaction.waitToPerform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                matcher,
                DescendantViewActions.checkDescendantViewAction(childMatcher, assertion)
            ).atPosition(0)
        )
    }
}