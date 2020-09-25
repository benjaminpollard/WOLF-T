package com.palringo.candidate

import com.palringo.candidate.requests.GroupRequest
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class GroupRequestIsCorrectTest {
    @Test
    @Throws(Exception::class)
    fun groupRequestIsCorrect() {
        val item = GroupRequest(2, extended = true, subscribe = true)
        Assert.assertEquals(item.asJson().toString(), "{" +
                "\"name\":\"group profile\"," +
                "\"body\":{" +
                "\"subscribe\":true," +
                "\"id\":2," +
                "\"extended\":true" +
                "}" +
                "}")
    }

}