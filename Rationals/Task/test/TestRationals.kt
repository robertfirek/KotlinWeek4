package rationals

import org.junit.Assert
import org.junit.Test

class TestRationals {
    @Test
    fun test_1_0() {
        Assert.assertTrue("expected 1/2 but  ${(2 divBy 4)}", (2 divBy 4).toString() == "1/2")
        Assert.assertTrue("expected -1/2 but  ${(-1 divBy 2)}", (-1 divBy 2).toString() == "-1/2")
        Assert.assertTrue("expected 2 but  ${(2 divBy 1)}", (2 divBy 1).toString() == "2")
        Assert.assertTrue("expected -1 but  ${(-1 divBy 1)}", (-1 divBy 1).toString() == "-1")
        Assert.assertTrue("expected -1 but  ${(1 divBy -1)}", (1 divBy -1).toString() == "-1")
        Assert.assertTrue("expected 13/122 but  ${(117 divBy 1098)}", (117 divBy 1098).toString() == "13/122")
        Assert.assertTrue("expected 13/122 but  ${"117/1098".toRational()}", "117/1098".toRational().toString() == "13/122")
        Assert.assertTrue("expected 1/2 but  ${"912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger()}", "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
    }
    @Test
    fun test0() {
        val r1 = 1 divBy 2
        val r2 = 2000000000L divBy 4000000000L
        Assert.assertEquals("""Wrong result for
            |val r1 = 1 divBy 2
            |val r2 = 2000000000L divBy 4000000000L
            |r1 == r2""".trimMargin(),
                true, r1 == r2)
    }

    @Test
    fun test1() {
        Assert.assertEquals("Wrong result for\n(2 divBy 1).toString() == \"2\"",
                true, (2 divBy 1).toString() == "2")
    }

    @Test
    fun test2() {
        Assert.assertEquals("Wrong result for\n(-2 divBy 4).toString() == \"-1/2\"",
                true, (-2 divBy 4).toString() == "-1/2")
    }

    @Test
    fun test3() {
        Assert.assertEquals("Wrong result for\n\"117/1098\".toRational().toString() == \"13/122\"",
                true, "117/1098".toRational().toString() == "13/122")
    }

    @Test
    fun test4() {
        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() - \"1/3\".toRational() == \"1/6\".toRational()",
                true, "1/2".toRational() - "1/3".toRational() == "1/6".toRational())
        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() + \"1/3\".toRational() == \"5/6\".toRational()",
                true, "1/2".toRational() + "1/3".toRational() == "5/6".toRational())
    }
    @Test
    fun test4_0() {
        println("1/2".toRational() )
        println("1/3".toRational())
        println("1/2".toRational() - "1/3".toRational() )

        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() + \"1/3\".toRational() == \"5/6\".toRational()",
                true, "1/2".toRational() + "1/3".toRational() == "5/6".toRational())
        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() - \"1/3\".toRational() == \"1/6\".toRational()",
                true, "1/2".toRational() - "1/3".toRational() == "1/6".toRational())
    }

    @Test
    fun test5() {
        Assert.assertEquals("Wrong result for\n-(1 divBy 2) == (-1 divBy 2)",
                true, -(1 divBy 2) == (-1 divBy 2))
    }

    @Test
    fun test6() {
        Assert.assertEquals("Wrong result for\n(1 divBy 2) * (1 divBy 3) == \"1/6\".toRational()",
                true, (1 divBy 2) * (1 divBy 3) == "1/6".toRational())
        Assert.assertEquals("Wrong result for\n(1 divBy 2) / (1 divBy 4) == \"2\".toRational()",
                true, (1 divBy 2) / (1 divBy 4) == "2".toRational())
    }

    @Test
    fun test7() {
        Assert.assertEquals("Wrong result for\n(1 divBy 2) < (2 divBy 3)",
                true, (1 divBy 2) < (2 divBy 3))
        Assert.assertEquals("Wrong result for\n(1 divBy 2) in (1 divBy 3)..(2 divBy 3)",
                true, (1 divBy 2) in (1 divBy 3)..(2 divBy 3))
    }

    @Test
    fun test8() {
        Assert.assertEquals("Wrong result for\n" +
                "\"912016490186296920119201192141970416029\".toBigInteger() divBy " +
                "\"1824032980372593840238402384283940832058\".toBigInteger() == 1 divBy 2",
                true, "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
    }
}