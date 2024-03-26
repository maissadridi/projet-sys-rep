package rmi;

import java.rmi.*;

public class TaskListClient {
    public static void main(String[] args) {
        try {
            TaskListInterface obj = (TaskListInterface) Naming.lookup("//localhost/TaskListServer");
            //test
            obj.addTask("Task 1");
            obj.addTask("Task 2");
            obj.addTask("Task 3");
            obj.removeTask(1);
            obj.getTasks().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
