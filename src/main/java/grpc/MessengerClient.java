package grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.*;

public class MessengerClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        MessengerGrpc.MessengerBlockingStub stub = MessengerGrpc.newBlockingStub(channel);

        // Envoi d'un message
        MessageRequest messageRequest = MessageRequest.newBuilder()
                .setSender("Alice")
                .setRecipient("Bob")
                .setMessage("Hello Bob!")
                .build();
        MessageResponse messageResponse = stub.sendMessage(messageRequest);
        System.out.println("Status: " + messageResponse.getStatus());

        // Récupération des messages pour un utilisateur
        UserRequest userRequest = UserRequest.newBuilder().setUsername("Bob").build();
        MessageList messageList = stub.getMessagesForUser(userRequest);
        for (String message : messageList.getMessagesList()) {
            System.out.println("Message: " + message);
        }

        channel.shutdown();
    }
}
