package sample.service;

import javafx.collections.ObservableList;
import sample.beans.Task;
import sample.dao.DaoImpl;

import java.sql.SQLException;

/**
 * Created by LiShixi on 2014/6/28.
 */
public class TaskManager {
    private static TaskManager instance = null;
    private DaoImpl dao;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();

        }
        return instance;
    }

    public ObservableList<Task> list() {

        try {
            return dao.list();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insert(Task task) {
        try {
            dao.insert(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
