package micropost.data.transport

import micropost.core.HalBuilder
import org.springframework.hateoas.ResourceSupport

class PagingResource() : ResourceSupport() {

    var page: Int? = null
    var size: Int? = null
    var totalItems: Int? = null
    var totalPages: Int? = null

    constructor(pageArg: Int? = 0,
                sizeArg: Int? = 0,
                totalArg: Int,
                pathArg: String) : this() {

        page = pageArg
        size = sizeArg
        totalItems = totalArg

        val hal = HalBuilder.forPath(pathArg)

        if (sizeArg == null || sizeArg < 1) return

        val safePage = pageArg!!
        totalPages = totalArg / sizeArg
        val lastPage = totalPages!! - 1

        with(this) {
            if (lastPage > 0) {
                add(hal.buildLink("first", pathArg.withPaging(), 0, sizeArg))
            }

            if (safePage > 0) {
                add(hal.buildLink("prev", pathArg.withPaging(), safePage - 1, sizeArg))
            }

            if (safePage < lastPage) {
                add(hal.buildLink("next", pathArg.withPaging(), safePage + 1, sizeArg))
            }

            if (lastPage > 0) {
                add(hal.buildLink("last", pathArg.withPaging(), lastPage, sizeArg))
            }
        }
    }

    private fun String.withPaging(): String {
        val prefix = if (this.contains("?")) "&" else "?"
        return prefix + "page={page}&size={size}"
    }
}