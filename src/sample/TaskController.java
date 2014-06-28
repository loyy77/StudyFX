package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import sample.beans.Task;
import sample.service.TaskManager;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskController {

    @FXML
    final ObservableList<Task> taskDatas = TaskManager.getInstance().list();
    StringConverter sc = new StringConverter() {
        @Override
        public String toString(Object object) {
            return object == null ? null : object.toString();
        }

        @Override
        public Object fromString(String string) {
            return string;
        }
    };
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private Button btnOk;
    @FXML
    private TextField txtInput;
    @FXML
    private ListView<Task> listSort;

    @FXML
    void taskAdd(ActionEvent event) {

        String input = txtInput.getText();
        Task task=new Task(1, input);
        TaskManager.getInstance().insert(task);
        taskDatas.add(task);
        taskTableView.setItems(TaskManager.getInstance().list());

    }

    @FXML
    void initialize() {
        System.out.println(taskDatas.toString());
        taskTableView.setItems(taskDatas);

        TableColumn status = (TableColumn) getTaskTableView().getColumns().get(0);
        TableColumn title = (TableColumn) getTaskTableView().getColumns().get(1);
        //        设置单元格的值绑定工厂
        status.setCellValueFactory(new PropertyValueFactory("status"));
        //        设置单元格的类型工厂，可以实现单元格放入其他控件
        status.setCellFactory(CheckBoxTableCell.forTableColumn(status));
        status.setMinWidth(120);

        title.setCellValueFactory(new PropertyValueFactory("title"));
        title.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        taskTableView.setEditable(true);

    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public TableView<Task> getTaskTableView() {
        return taskTableView;
    }

    public void setTaskTableView(TableView<Task> taskTableView) {
        this.taskTableView = taskTableView;
    }

    public Button getBtnOk() {
        return btnOk;
    }

    public void setBtnOk(Button btnOk) {
        this.btnOk = btnOk;
    }

    public TextField getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(TextField txtInput) {
        this.txtInput = txtInput;
    }

    public ListView<Task> getListSort() {
        return listSort;
    }

    public void setListSort(ListView<Task> listSort) {
        this.listSort = listSort;
    }
}
