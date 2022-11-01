/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.registration;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLenghtErr;
    private String passwordLenghtErr;
    private String fullNameLenghtErr;
    private String confirmNoMatched;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    /**
     * @return the usernameLenghtErr
     */
    public String getUsernameLenghtErr() {
        return usernameLenghtErr;
    }

    /**
     * @param usernameLenghtErr the usernameLenghtErr to set
     */
    public void setUsernameLenghtErr(String usernameLenghtErr) {
        this.usernameLenghtErr = usernameLenghtErr;
    }

    /**
     * @return the passwordLenghtErr
     */
    public String getPasswordLenghtErr() {
        return passwordLenghtErr;
    }

    /**
     * @param passwordLenghtErr the passwordLenghtErr to set
     */
    public void setPasswordLenghtErr(String passwordLenghtErr) {
        this.passwordLenghtErr = passwordLenghtErr;
    }

    /**
     * @return the fullNameLenghtErr
     */
    public String getFullNameLenghtErr() {
        return fullNameLenghtErr;
    }

    /**
     * @param fullNameLenghtErr the fullNameLenghtErr to set
     */
    public void setFullNameLenghtErr(String fullNameLenghtErr) {
        this.fullNameLenghtErr = fullNameLenghtErr;
    }

    /**
     * @return the confirmNoMatched
     */
    public String getConfirmNoMatched() {
        return confirmNoMatched;
    }

    /**
     * @param confirmNoMatched the confirmNoMatched to set
     */
    public void setConfirmNoMatched(String confirmNoMatched) {
        this.confirmNoMatched = confirmNoMatched;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
}
