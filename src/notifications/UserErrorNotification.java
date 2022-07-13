package notifications;

import usererror.UserError;

public class UserErrorNotification {
    UserError userError;
    public UserErrorNotification(UserError userError){
        this.userError = userError;
    }

    public UserError getUserError() {
        return userError;
    }

    public void setUserError(UserError userError) {
        this.userError = userError;
    }

}
