package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.beans.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiShixi on 2014/6/28.
 */
public class DaoImpl {
    private static DaoImpl instance = null;
    Connection connection = null;

    private DaoImpl() {
    }

    public static DaoImpl getInstance() {
        if (instance == null) {
            instance = new DaoImpl();
        }
        return instance;
    }

    public static void main(String[] args) throws SQLException {
        insert(new Task(1, "lishixi"));
        System.out.println(list());
    }

    private static void createTable() throws SQLException {
        Connection conn = DaoImpl.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("create table task(status int,title varchar(255))");
        ps.execute();
    }

    public static void insert(Task task) throws SQLException {
        Connection conn = DaoImpl.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into task(status,title) values(?,?)");
        ps.setInt(1, task.getStatus());
        ps.setString(2, task.getTitle());
        int count = ps.executeUpdate();
        if (count > 0) {

            System.out.println(count + "insert successfull!" + task.toString());
        }

    }

    public static ObservableList<Task> list() throws SQLException {
        Connection conn = DaoImpl.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from task");
        ResultSet rs = ps.executeQuery();
        Task task = null;
        ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
        List<Task> tasks = new ArrayList<Task>();

        while (rs.next()) {
            task = new Task();
            task.setStatus(Integer.valueOf(rs.getString("status")));
            task.setTitle((rs.getString("title")));
            tasks.add(task);
        }
        taskObservableList.addAll(tasks);
        return taskObservableList;

    }

    public Connection getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:sample;create=true");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
