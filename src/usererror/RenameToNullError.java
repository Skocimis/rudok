package usererror;

public class RenameToNullError extends UserError {
    public RenameToNullError() {
        super("<html>Node must have a name. </html>");

    }
}
