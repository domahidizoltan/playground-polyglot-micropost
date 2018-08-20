package micropost.core

import org.jooq.Record
import org.jooq.SelectConnectByStep
import org.springframework.hateoas.Link
import org.springframework.web.util.UriComponentsBuilder
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

fun SelectConnectByStep<out Record>.paginate(page: Int?, size: Int?): SelectConnectByStep<out Record> {
    size?.let {
        val pageValue = page ?: 0
        this.offset(it * pageValue).limit(it)
    }
    return this
}

class HalBuilder(private val rootPath: String) {

    companion object {
        fun forPath(path: String): HalBuilder = HalBuilder(path)
    }

    fun buildLink(rel: String, subPath: String, vararg parameters: Any): Link {
        val regex = "\\{(.*?)\\}".toRegex()
        val params = regex.findAll(subPath)
                .asSequence()
                .zip(parameters.asSequence()) { key, param -> Pair(key.groups[1]?.value, param.toString()) }
                .toMap()

        val uri = UriComponentsBuilder
                .fromPath(rootPath + subPath)
                .buildAndExpand(params)
                .toUriString()

        return Link(uri, rel)
    }

}