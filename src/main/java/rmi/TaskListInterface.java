package rmi;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public interface TaskListInterface extends Remote {
    void addTask(String task) throws RemoteException;
    void removeTask(int index) throws RemoteException;
    ArrayList<String> getTasks() throws RemoteException;
}