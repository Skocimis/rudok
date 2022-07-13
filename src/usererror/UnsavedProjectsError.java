package usererror;

public class UnsavedProjectsError extends UserError {
    public UnsavedProjectsError(Object name) {
        super("");
        if(name instanceof String string){
            setText("<html>Project "+string+" will not be saved to workspace because it is not saved to disk. </html>");
        }
    }
}
