package micropost.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import micropost.data.Tables.POST_STATISTICS
import micropost.proto.PostStatisticsGrpc
import micropost.proto.PostStatisticsOuterClass.PostStatisticsRequest
import micropost.proto.PostStatisticsOuterClass.PostStatisticsResponse
import org.jooq.DSLContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class PostStatisticsClient(host: String,
                           port: Int,
                           private val responseObserver: PostStatisticsResponseObserver) {
    val channel: ManagedChannel = ManagedChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .build()

    val asyncStub = PostStatisticsGrpc.newStub(channel)

    fun sendPostStatistics(postId: Int, content: String) {
        println("generate for $postId")
        val request = PostStatisticsRequest.newBuilder()
                .setPostId(postId)
                .setContent(content)
                .build()
        asyncStub.generatePostStatistics(request, responseObserver)
    }

}


class PostStatisticsResponseObserver(
        private val jooq: DSLContext,
        private val objectMapper: ObjectMapper
): StreamObserver<PostStatisticsResponse> {
    private val log: Logger = LoggerFactory.getLogger(PostStatisticsResponseObserver::class.java)

    override fun onNext(response: PostStatisticsResponse?) {
        val postId = response!!.postId
        val wordOccurrenceJsonString = objectMapper.writeValueAsString(response.wordOccurrenceMap)
        val statistics = jooq.selectFrom(POST_STATISTICS).where(POST_STATISTICS.POST_ID.eq(postId)).fetchOne()
        if (statistics == null) {
            jooq.insertInto(POST_STATISTICS)
                    .columns(
                            POST_STATISTICS.POST_ID,
                            POST_STATISTICS.TOTAL_WORDS,
                            POST_STATISTICS.ALPHANUMERIC,
                            POST_STATISTICS.NON_ALPHANUMERIC,
                            POST_STATISTICS.WORD_OCCURRENCE
                    )
                    .values(
                            postId,
                            response.totalWords,
                            response.alphanumeric,
                            response.nonAlphanumeric,
                            wordOccurrenceJsonString
                    )
                    .execute()
        } else {
            jooq.update(POST_STATISTICS)
                    .set(POST_STATISTICS.TOTAL_WORDS, response.totalWords)
                    .set(POST_STATISTICS.ALPHANUMERIC, response.alphanumeric)
                    .set(POST_STATISTICS.NON_ALPHANUMERIC, response.nonAlphanumeric)
                    .set(POST_STATISTICS.WORD_OCCURRENCE, wordOccurrenceJsonString)
                    .where(POST_STATISTICS.POST_ID.eq(postId))
                    .execute()
        }

        log.debug("Post statistics saved for $postId")
    }

    override fun onError(t: Throwable?) {
        log.error("Could not store statistics response: ", t)
    }

    override fun onCompleted() {
        log.debug("PostStatisticsResponse received")
    }

}