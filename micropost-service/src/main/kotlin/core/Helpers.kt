package micropost.core

import org.jooq.Record
import org.jooq.SelectWhereStep
import javax.validation.Validator

fun Validator.validateRequest(candidate: Any) {
    val constraints = this.validate(candidate)
            .iterator()
            .asSequence()
            .map { "${it.propertyPath} --> ${it.message}" }
            .toList()

    if (!constraints.isEmpty()) {
        throw IllegalArgumentException(constraints.toString())
    }
}

fun SelectWhereStep<out Record>.paginate(page: Int?, size: Int?): SelectWhereStep<out Record> {
    size?.let {
        val pageValue = page ?: 0
        this.offset(it * pageValue).limit(it)
    }
    return this
}