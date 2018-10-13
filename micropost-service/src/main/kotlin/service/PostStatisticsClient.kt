package micropost.service

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import micropost.proto.PostStatisticsGrpc
import micropost.proto.PostStatisticsOuterClass.PostStatisticsRequest
import micropost.proto.PostStatisticsOuterClass.PostStatisticsResponse
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


class PostStatisticsResponseObserver: StreamObserver<PostStatisticsResponse> {
    private val log: Logger = LoggerFactory.getLogger(PostStatisticsResponseObserver::class.java)

    override fun onNext(response: PostStatisticsResponse?) {
        println("next $response")
    }

    override fun onError(t: Throwable?) {
        log.error("Could not store statistics response: ", t)
    }

    override fun onCompleted() {
        log.debug("PostStatisticsResponse received")
    }

}