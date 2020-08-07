package DemoStorage;

/**
 * @author Ҧ����
 * 2020/6/17,11:45
 * ��ֵĳ���������
 */

import dataLayer.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserLogin extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {


        primaryStage.setTitle("���� by:H17000623Ҧ����");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));// �������, Insetsʵ�����þ���������ıߵ�һ����ƫ����

        Text title = new Text("��¼");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label Account = new Label("�˻�:");
        final TextField userAccount = new TextField();
        Label pwd = new Label("����:");
        final PasswordField userPwd = new PasswordField();
        Label no = new Label("û���˺�?->");
        no.setTextFill(Color.BLUE);
        Button confirm = new Button("ȷ��");
        Button register = new Button("ע��");
        Button forget = new Button("��������");

        confirm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (MyLoginIn.findUser(userAccount.getText(), userPwd.getText())) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("�ɹ���ʾ");
                    alert.setHeaderText(null);
                    alert.setContentText("��¼�ɹ�");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("������ʾ");
                    alert.setHeaderText(null);
                    alert.setContentText("�û������������,����������");
                    alert.showAndWait();
                    userAccount.clear();
                    userPwd.clear();
                }
            }

        });

        register.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                final User user = MyLoginIn.newUser();
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("�ɹ���ʾ");
                alert.setHeaderText(null);
                alert.setContentText("�����˻�Ϊ: " + user.getAccount());
                alert.showAndWait();
                dialog.setTitle("��������");
                final PasswordField newPwd = new PasswordField();
                Label label = new Label("����");
                Button confirm = new Button("ȷ��");
                confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        alert.setTitle("�ɹ���ʾ");
                        alert.setHeaderText(null);
                        alert.setContentText("ע��ɹ�,�뷵�ص�¼");
                        user.setPassword(newPwd.getText());
                        MyLoginIn.addNewUser(user);
                        alert.showAndWait();
                        dialog.close();
                        userAccount.setText(user.getAccount());
                        userPwd.clear();
                    }
                });
                GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));// �������, Insetsʵ�����þ���������ıߵ�һ����ƫ����
                grid.add(label, 0, 1);
                grid.add(newPwd, 1, 1);
                grid.add(confirm, 1, 2);
                dialog.setScene(new Scene(grid, 300, 100));
                dialog.show();
            }
        });

        forget.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                final User user = MyLoginIn.findAccount(userAccount.getText());
                if (user != null) {
                    final Stage dialog = new Stage();
                    dialog.setTitle("��������");
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    final PasswordField newPwd = new PasswordField();
                    Label label = new Label("������");
                    Button confirm = new Button("ȷ��");
                    confirm.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            user.setPassword(newPwd.getText());
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("�ɹ���ʾ");
                            alert.setHeaderText(null);
                            alert.setContentText("�޸�����ɹ�,�뷵�ص�¼");
                            alert.showAndWait();
                            dialog.close();
                            userPwd.clear();
                        }
                    });
                    GridPane grid = new GridPane();
                    grid.setAlignment(Pos.CENTER);
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(25, 25, 25, 25));// �������, Insetsʵ�����þ���������ıߵ�һ����ƫ����
                    grid.add(label, 0, 1);
                    grid.add(newPwd, 1, 1);
                    grid.add(confirm, 1, 2);
                    dialog.setScene(new Scene(grid, 300, 100));
                    dialog.show();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("������ʾ");
                    alert.setHeaderText(null);
                    alert.setContentText("��������˻�������,����������");
                    alert.showAndWait();
                    userAccount.clear();
                    userPwd.clear();
                }
            }
        });
        grid.add(title, 0, 0, 2, 1);
        grid.add(Account, 0, 1);
        grid.add(userAccount, 1, 1);
        grid.add(pwd, 0, 2);
        grid.add(userPwd, 1, 2);

        HBox panel = new HBox(40);
        panel.setAlignment(Pos.BOTTOM_RIGHT);
        panel.getChildren().add(confirm);

        HBox panel2 = new HBox(40);
        panel2.setAlignment(Pos.BOTTOM_RIGHT);
        panel2.getChildren().add(no);
        panel2.getChildren().add(register);
        grid.add(panel, 1, 4);
        grid.add(panel2, 1, 5);

        HBox panel3 = new HBox(20);
        panel3.setAlignment(Pos.BOTTOM_RIGHT);
        panel3.getChildren().add(forget);
        grid.add(panel3, 1, 6);
        primaryStage.setScene(new Scene(grid, 350, 300));

        primaryStage.show();
    }
}
