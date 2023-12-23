package com.example.be.model;

public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String newComfirmPassword;

    public ChangePassword() {
    }

    public ChangePassword(String oldPassword, String newPassword, String newComfirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newComfirmPassword = newComfirmPassword;
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

    public String getNewComfirmPassword() {
        return newComfirmPassword;
    }

    public void setNewComfirmPassword(String newComfirmPassword) {
        this.newComfirmPassword = newComfirmPassword;
    }
}
