package rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.ArrayList;

public class TaskListImpl extends UnicastRemoteObject implements TaskListInterface {
    private ArrayList<String> tasks;

    public TaskListImpl() throws RemoteException {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) throws RemoteException {
        tasks.add(task);
    }

    public void removeTask(int index) throws RemoteException {
        tasks.remove(index);
    }

    public ArrayList<String> getTasks() throws RemoteException {
        return tasks;
    }

    public static void main(String[] args) {
        try {
            TaskListImpl obj = new TaskListImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TaskListServer", obj);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
