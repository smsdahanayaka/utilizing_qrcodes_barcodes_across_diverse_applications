package modal;

public class User {
    private int userId;
    private String userName;
    private String userContactNumber;
    private String userMail;
    private String userPassword;

    public User() {
    }

    public User(int userId, String userName, String userContactNumber, String userMail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userContactNumber = userContactNumber;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
