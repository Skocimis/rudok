package usererror;

import notifications.UserErrorNotification;
import observer.IPublisher;
import observer.ISubscriber;
import observer.MyPublisher;

public class UserErrorFactory extends MyPublisher {

    public void createUserError(String errorType){
        String basicstr = errorType.toUpperCase().replaceAll("\\s+","");
        UserError myError = switch (basicstr) {
            case "CHILDTONOTHING" -> new AddChildToNothingError();
            case "PRESENTATIONTOWORKSPACE" -> new AddPresentationToWorkspaceError();
            case "SLIDETOWORKSPACE" -> new AddSlideToWorkspaceError();
            case "SLIDETOPROJECT" -> new AddSlideToProjectError();
            case "DELETEWORKSPACE" -> new DeleteWorkspaceError();
            case "RENAMETONULL" -> new RenameToNullError();
            default ->
                    new UnimplementedError();
        };
        notifySubscribers(new UserErrorNotification(myError));
    }
    public void createUserError(String errorType, Object data){
        String basicstr = errorType.toUpperCase().replaceAll("\\s+","");
        UserError myError = switch (basicstr) {
            case "UNSAVEDPROJECTS" -> new UnsavedProjectsError(data);
            default ->
                    new UnimplementedError();
        };
        notifySubscribers(new UserErrorNotification(myError));
    }


}
