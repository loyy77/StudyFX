package sample.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by LiShixi on 2014/6/27.
 */
public class Task {
    private IntegerProperty status=new SimpleIntegerProperty(1);
    private StringProperty title=new SimpleStringProperty();

    public Task(int status, String title) {
       setStatus(status);
      setTitle(title);
    }

    public Task() {
    }

    public int getStatus() {
        return status.get();
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return "Task{" +
                       "status=" + status +
                       ", title=" + title +
                       '}';
    }
}
