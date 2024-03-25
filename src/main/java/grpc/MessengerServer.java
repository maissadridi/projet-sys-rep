package grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import grpc.MessengerGrpc.MessengerImplBase;

public class MessengerServer {
    private int port = 50051;
    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(new MessengerImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                MessengerServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        final MessengerServer server = new MessengerServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class MessengerImpl extends grpc.MessengerImplBase {
        @Override
        public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            // Implémentez la logique de l'envoi de message ici
            String status = "Message sent successfully to " + request.getRecipient();
            MessageResponse response = MessageResponse.newBuilder().setStatus(status).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getMessagesForUser(UserRequest request, StreamObserver<MessageList> responseObserver) {
            // Implémentez la logique de récupération des messages ici
            String username = request.getUsername();
            MessageList messageList = MessageList.newBuilder()
                    .addMessages("Message 1 for " + username)
                    .addMessages("Message 2 for " + username)
                    .build();
            responseObserver.onNext(messageList);
            responseObserver.onCompleted();
        }
    }
}

