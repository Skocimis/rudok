package usererror;

public class AddChildToNothingError extends UserError {
    public AddChildToNothingError() {
        super("<html>You have to select the parent node. </html>");
    }
}
