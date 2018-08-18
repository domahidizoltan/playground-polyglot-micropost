package micropost.core

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