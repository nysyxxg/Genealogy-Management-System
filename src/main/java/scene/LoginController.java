package scene;

import dataLayer.Common;
import dataLayer.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ҧ����
 * 2020/6/17,14:20
 * ��ֵĳ���������
 */
public class LoginController implements Initializable {

    public TextField userAccount;
    public Button loginButton;
    public Button registerButton;
    public PasswordField userPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Common.userList = Main.loadUserInfo();
    }

    //��ʼ��¼�Լ����
    public void login(ActionEvent actionEvent) {
        if (User.ifInfoOK(userAccount.getText(),userPassword.getText())){
            System.out.println("valid account and password");
            Common.currentStage.close();
            System.out.println("login scene closed");
            Main.startJiapuScene();
        }else {
            Common.sendAlert(false,"�û������������,����������");
        }
    }

    public void register(ActionEvent actionEvent) {

        //�������ظ��û���
        if (User.ifNameExist(userAccount.getText())){
            Common.sendAlert(false,"�û����Ѵ���,����������");
            return;
        }
        //���������λ������
        if(userPassword.getText().length()<6){
            Common.sendAlert(false,"����δ�ﵽ��λ�����ϣ�����������");
            return;
        }
        Common.userList.add(new User(userAccount.getText(),userPassword.getText()));
        Common.sendAlert(true,"�ɹ�������ͨ�˻�����ص���¼�����¼");
    }


}
