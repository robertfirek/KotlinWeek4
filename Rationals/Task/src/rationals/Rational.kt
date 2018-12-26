package rationals

import java.math.BigInteger

infix fun Int.divBy(denominator: Int): Rational = Rational(numerator = this.toBigInteger(), denominator = denominator.toBigInteger())
infix fun Long.divBy(denominator: Long): Rational = Rational(numerator = this.toBigInteger(), denominator = denominator.toBigInteger())
infix fun BigInteger.divBy(denominator: BigInteger): Rational = Rational(numerator = this, denominator = denominator)

fun String.toRational(): Rational = when {
    this.contains('/') -> Rational(this.split('/')[0].toBigInteger(), this.split('/')[1].toBigInteger())
    else -> Rational(this.toBigInteger(), BigInteger.ONE)
}

class Rational(numerator: BigInteger, denominator: BigInteger) : Comparable<Rational> {

    private val numerator: BigInteger
    private val denominator: BigInteger

    init {
        val greatestCommonDivisor = greatestCommonDivisor(numerator, denominator)

        this.numerator = if (denominator / greatestCommonDivisor < BigInteger.ZERO) -(numerator / greatestCommonDivisor) else numerator / greatestCommonDivisor
        this.denominator = if (denominator / greatestCommonDivisor < BigInteger.ZERO) -(denominator / greatestCommonDivisor) else denominator / greatestCommonDivisor
    }

    operator fun plus(other: Rational): Rational {
        if (this == 0 divBy 1) return other
        if (other == 0 divBy 1) return this

        val numeratorGreatestCommonDivisor = greatestCommonDivisor(this.numerator, other.numerator)
        val denominatorGreatestCommonDivisor = greatestCommonDivisor(this.denominator, other.denominator)

        // maths magic :D
        val newNumerator =
                ((this.numerator / numeratorGreatestCommonDivisor) *
                        (other.denominator / denominatorGreatestCommonDivisor) +
                        (other.numerator / numeratorGreatestCommonDivisor) *
                        (this.denominator / denominatorGreatestCommonDivisor)
                        ) * numeratorGreatestCommonDivisor
        val newDenominator = leastCommonMultiple(this.denominator, other.denominator)

        return Rational(newNumerator, newDenominator)
    }

    operator fun minus(other: Rational): Rational = this + (-other)
    operator fun unaryMinus(): Rational = Rational(-this.numerator, this.denominator)
    operator fun times(other: Rational): Rational {
        // maths magic :D
        val c = Rational(this.numerator, other.denominator)
        val d = Rational(other.numerator, this.denominator)
        return Rational(c.numerator * d.numerator, c.denominator * d.denominator)
    }

    operator fun div(other: Rational): Rational {
        val reciprocalOfOther = Rational(other.denominator, other.numerator)
        return this.times(reciprocalOfOther)
    }

    operator fun rangeTo(endInclusive: Rational): ClosedRange<Rational> = object : ClosedRange<Rational> {
        override val endInclusive: Rational
            get() = endInclusive
        override val start: Rational
            get() = this@Rational
    }

    override operator fun compareTo(other: Rational): Int {
        val leftHandSide = this.numerator * other.denominator
        val rightHandSide = this.denominator * other.numerator
        if (leftHandSide < rightHandSide) return -1
        return if (leftHandSide > rightHandSide) +1 else 0
    }


    override fun toString(): String {
        return if (denominator == BigInteger.ONE) numerator.toString() else "$numerator/$denominator"
    }

    private fun leastCommonMultiple(first: BigInteger, second: BigInteger): BigInteger {
        val firstPositive = if (first > BigInteger.ZERO) first else -first
        val secondPositive = if (second > BigInteger.ZERO) second else -second
        return firstPositive * (secondPositive / greatestCommonDivisor(firstPositive, secondPositive))

    }

    private fun greatestCommonDivisor(first: BigInteger, second: BigInteger): BigInteger {
        val firstPositive = if (first > BigInteger.ZERO) first else -first
        val secondPositive = if (second > BigInteger.ZERO) second else -second

        return if (secondPositive == BigInteger.ZERO) first else greatestCommonDivisor(secondPositive, firstPositive % secondPositive)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return this.compareTo(other as Rational) == 0
    }

    override fun hashCode(): Int {
        return 31 * numerator.hashCode() + denominator.hashCode()
    }

}

fun main(args: Array<String>) {
    val r1 = 1 divBy 2
    val r2 = 2000000000L divBy 4000000000L
    println(r1 == r2)

    println((2 divBy 1).toString() == "2")

    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    println("1/2".toRational() - "1/3".toRational() == "1/6".toRational())
    println("1/2".toRational() + "1/3".toRational() == "5/6".toRational())

    println(-(1 divBy 2) == (-1 divBy 2))

    println((1 divBy 2) * (1 divBy 3) == "1/6".toRational())
    println((1 divBy 2) / (1 divBy 4) == "2".toRational())

    println((1 divBy 2) < (2 divBy 3))
    println((1 divBy 2) in (1 divBy 3)..(2 divBy 3))

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}