package io.dellisd.github.mmapp

@DslMarker
annotation class ExpressionDsl

class ExpressionBuilder

open class Expression(val operator: String?, vararg val arguments: Expression) {
    constructor() : this(null)
}

open class ExpressionLiteral(protected val literal: Any) : Expression()

fun ExpressionBuilder.literal(number: Number) = ExpressionLiteral(number)
fun ExpressionBuilder.literal(string: String) = ExpressionLiteral(string)
fun ExpressionBuilder.literal(boolean: Boolean) = ExpressionLiteral(boolean)

fun ExpressionBuilder.rgba(red: Number, green: Number, blue: Number, alpha: Number) =
    rgba(literal(red), literal(green), literal(blue), literal(alpha))

fun ExpressionBuilder.rgba(red: Expression, green: Expression, blue: Expression, alpha: Expression) =
    Expression("rgba", red, green, blue, alpha)

fun ExpressionBuilder.eq(compareOne: Expression, compareTwo: Expression) = Expression("==", compareOne, compareTwo)

fun ExpressionBuilder.get(input: Expression) = Expression("get", input)
fun ExpressionBuilder.get(input: String) = get(literal(input))