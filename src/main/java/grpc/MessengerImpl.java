package grpc;

import io.grpc.stub.StreamObserver;

public class MessengerImpl extends MessengerGrpc.MessengerImplBase {

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        // Implement the message sending logic here
        String status = "Message sent successfully to " + request.getRecipient();
        MessageResponse response = MessageResponse.newBuilder().setStatus(status).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(UserRequest request, StreamObserver<MessageList> responseObserver) {
        // Implement the message retrieval logic here
        String username = request.getUsername();
        MessageList messageList = MessageList.newBuilder()
                .addMessages("Message 1 for " + username)
                .addMessages("Message 2 for " + username)
                .build();
        responseObserver.onNext(messageList);
        responseObserver.onCompleted();
    }
}