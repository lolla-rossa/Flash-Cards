package DataBaseControl;

import MainForm.ErrorMessageForm;
import MainForm.SuccessMessageForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckEnterData extends CreateConnection implements Request {

	private String login ;
	private char[] password;
	boolean correctness = false;

	public CheckEnterData(String login, char[] password) {
		setLogin(login);
		setPassword(password);
	}

	public boolean check() {
		getRequest("select * from Users where login = '" + login+ "' and password = '" + String.valueOf(password) +"'");
		return correctness;
	}

	public void setLogin(String newLogin) {
		login = newLogin;
	}
	public void setPassword(char[] newPassword) {
		password = newPassword;
	}

	public void getRequest(String sql){
		try {
            Statement st = connection.createStatement();
			ResultSet resultSet  = st.executeQuery(sql);
			if (resultSet.isClosed()) {
				ErrorMessageForm error = new ErrorMessageForm("Введенные данные некорректны");
				error.pack();
				return;
			}
			int k = st.executeUpdate("update Users set state = 1 where login = '" + login+ "' and password = '" + String.valueOf(password) +"'");
			if ( k == 0) {
                ErrorMessageForm error = new ErrorMessageForm ("Ошибка обновления состояния");
                error.pack();
            }
			correctness = true;
			return;
		}catch (Exception e){
			ErrorMessageForm error = new ErrorMessageForm (e.getMessage());
			error.pack();
            correctness = false;
		}
	}
}
