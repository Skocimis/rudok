package usererror;

public class UnimplementedError extends UserError {
    public UnimplementedError() {
        super("<html>You did something wrong. </html>");
    }
}
