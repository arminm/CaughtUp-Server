package news.caughtup.model;

/**
 * @author CaughtUp
 *
 */
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    
    public ChangePassword(String oldPassword, String newPassword) {
        super();
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
