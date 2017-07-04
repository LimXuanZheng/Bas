package login;

import java.util.Random;

public class LoginModel {
	private String newPass;
	
	public LoginModel() {
		super();
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public void generateNewPass() {
		int codeSize = 10;
		char[] chars = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < codeSize; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		setNewPass(output);
	}
	
	public void sendEmail(String receipientEmail) {
		SendEmail SE = new SendEmail();
		SE.sendEmail(receipientEmail, getNewPass());
	}

	public static void main(String[] args) {
		LoginModel LM = new LoginModel();
		LM.generateNewPass();
		System.out.println("New Password: " + LM.getNewPass());
	}

}
