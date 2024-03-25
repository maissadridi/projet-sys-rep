package rmi;

import java.rmi.*;

public class TaskListClient {
    public static void main(String[] args) {
        try {
            TaskListInterface obj = (TaskListInterface) Naming.lookup("//localhost/TaskListServer");
            // Utilisez les méthodes de l'interface TaskListInterface ici
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
