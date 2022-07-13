package actions;

import windows.dialogs.RenameNodeDialog;

public class ActionManager {
    private InfoAction infoAction;
    private NewProjectAction newProjectAction;
    private NewPresentationAction newPresentationAction;
    private NewSlideAction newSlideAction;
    private PresentationSettingsAction presentationSettingsAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private StartEditorModeAction startEditorModeAction;
    private StartSlideshowModeAction startSlideshowModeAction;
    private ToggleAddSlotStateAction toggleAddSlotStateAction;
    private ToggleDeleteSlotStateAction toggleDeleteSlotStateAction;
    private ToggleMoveSlotStateAction toggleMoveSlotStateAction;
    private ToggleResizeSlotStateAction toggleResizeSlotStateAction;
    private NewSlotPreferencesAction newSlotPreferencesAction;
    private AddChildAction addChildAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveProjectAction saveProjectAction;
    private SaveProjectAsAction saveProjectAsAction;
    private OpenProjectAction openProjectAction;
    private SharePresentationAction sharePresentationAction;
    private OpenWorkspaceAction openWorkspaceAction;
    private SaveWorkspaceAction saveWorkspaceAction;
    private SaveWorkspaceAsAction saveWorkspaceAsAction;
    private ImportPresentationAction importPresentationAction;
    private ExportPresentationAction exportPresentationAction;

    public ActionManager(){
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
        newPresentationAction = new NewPresentationAction();
        newSlideAction = new NewSlideAction();
        presentationSettingsAction = new PresentationSettingsAction();
        renameNodeAction = new RenameNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        startEditorModeAction = new StartEditorModeAction();
        startSlideshowModeAction = new StartSlideshowModeAction();
        toggleAddSlotStateAction = new ToggleAddSlotStateAction();
        toggleDeleteSlotStateAction = new ToggleDeleteSlotStateAction();
        toggleMoveSlotStateAction = new ToggleMoveSlotStateAction();
        newSlotPreferencesAction = new NewSlotPreferencesAction();
        toggleResizeSlotStateAction = new ToggleResizeSlotStateAction();
        addChildAction = new AddChildAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveProjectAction = new SaveProjectAction();
        saveProjectAsAction = new SaveProjectAsAction();
        openProjectAction = new OpenProjectAction();
        sharePresentationAction = new SharePresentationAction();
        openWorkspaceAction = new OpenWorkspaceAction();
        saveWorkspaceAction = new SaveWorkspaceAction();
        saveWorkspaceAsAction = new SaveWorkspaceAsAction();
        importPresentationAction = new ImportPresentationAction();
        exportPresentationAction = new ExportPresentationAction();

    }


    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }

    public PresentationSettingsAction getPresentationSettingsAction() {
        return presentationSettingsAction;
    }

    public void setPresentationSettingsAction(PresentationSettingsAction presentationSettingsAction) {
        this.presentationSettingsAction = presentationSettingsAction;
    }

    public NewPresentationAction getNewPresentationAction() {
        return newPresentationAction;
    }

    public void setNewPresentationAction(NewPresentationAction newPresentationAction) {
        this.newPresentationAction = newPresentationAction;
    }

    public NewSlideAction getNewSlideAction() {
        return newSlideAction;
    }

    public void setNewSlideAction(NewSlideAction newSlideAction) {
        this.newSlideAction = newSlideAction;
    }

    public RenameNodeAction getRenameNodeAction() {
        return renameNodeAction;
    }

    public void setRenameNodeAction(RenameNodeAction renameAction) {
        this.renameNodeAction = renameAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public void setDeleteNodeAction(DeleteNodeAction deleteNodeAction) {
        this.deleteNodeAction = deleteNodeAction;
    }

    public StartEditorModeAction getStartEditorModeAction() {
        return startEditorModeAction;
    }

    public void setStartEditorModeAction(StartEditorModeAction startEditorModeAction) {
        this.startEditorModeAction = startEditorModeAction;
    }

    public StartSlideshowModeAction getStartSlideshowModeAction() {
        return startSlideshowModeAction;
    }

    public void setStartSlideshowModeAction(StartSlideshowModeAction startSlideshowModeAction) {
        this.startSlideshowModeAction = startSlideshowModeAction;
    }

    public ToggleAddSlotStateAction getToggleAddSlotStateAction() {
        return toggleAddSlotStateAction;
    }

    public void setToggleAddSlotStateAction(ToggleAddSlotStateAction toggleAddSlotStateAction) {
        this.toggleAddSlotStateAction = toggleAddSlotStateAction;
    }

    public ToggleDeleteSlotStateAction getToggleDeleteSlotStateAction() {
        return toggleDeleteSlotStateAction;
    }

    public void setToggleDeleteSlotStateAction(ToggleDeleteSlotStateAction toggleDeleteSlotStateAction) {
        this.toggleDeleteSlotStateAction = toggleDeleteSlotStateAction;
    }

    public ToggleMoveSlotStateAction getToggleMoveSlotStateAction() {
        return toggleMoveSlotStateAction;
    }

    public void setToggleMoveSlotStateAction(ToggleMoveSlotStateAction toggleMoveSlotStateAction) {
        this.toggleMoveSlotStateAction = toggleMoveSlotStateAction;
    }

    public NewSlotPreferencesAction getNewSlotPreferencesAction() {
        return newSlotPreferencesAction;
    }

    public void setNewSlotPreferencesAction(NewSlotPreferencesAction newSlotPreferencesAction) {
        this.newSlotPreferencesAction = newSlotPreferencesAction;
    }

    public ToggleResizeSlotStateAction getToggleResizeSlotStateAction() {
        return toggleResizeSlotStateAction;
    }

    public void setToggleResizeSlotStateAction(ToggleResizeSlotStateAction toggleResizeSlotStateAction) {
        this.toggleResizeSlotStateAction = toggleResizeSlotStateAction;
    }

    public AddChildAction getAddChildAction() {
        return addChildAction;
    }

    public void setAddChildAction(AddChildAction addChildAction) {
        this.addChildAction = addChildAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public SaveProjectAsAction getSaveProjectAsAction() {
        return saveProjectAsAction;
    }

    public void setSaveProjectAsAction(SaveProjectAsAction saveProjectAsAction) {
        this.saveProjectAsAction = saveProjectAsAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public void setOpenProjectAction(OpenProjectAction openProjectAction) {
        this.openProjectAction = openProjectAction;
    }

    public SharePresentationAction getSharePresentationAction() {
        return sharePresentationAction;
    }

    public void setSharePresentationAction(SharePresentationAction sharePresentationAction) {
        this.sharePresentationAction = sharePresentationAction;
    }

    public OpenWorkspaceAction getOpenWorkspaceAction() {
        return openWorkspaceAction;
    }

    public void setOpenWorkspaceAction(OpenWorkspaceAction openWorkspaceAction) {
        this.openWorkspaceAction = openWorkspaceAction;
    }

    public SaveWorkspaceAction getSaveWorkspaceAction() {
        return saveWorkspaceAction;
    }

    public void setSaveWorkspaceAction(SaveWorkspaceAction saveWorkspaceAction) {
        this.saveWorkspaceAction = saveWorkspaceAction;
    }

    public SaveWorkspaceAsAction getSaveWorkspaceAsAction() {
        return saveWorkspaceAsAction;
    }

    public void setSaveWorkspaceAsAction(SaveWorkspaceAsAction saveWorkspaceAsAction) {
        this.saveWorkspaceAsAction = saveWorkspaceAsAction;
    }

    public ImportPresentationAction getImportPresentationAction() {
        return importPresentationAction;
    }

    public void setImportPresentationAction(ImportPresentationAction importPresentationAction) {
        this.importPresentationAction = importPresentationAction;
    }

    public ExportPresentationAction getExportPresentationAction() {
        return exportPresentationAction;
    }

    public void setExportPresentationAction(ExportPresentationAction exportPresentationAction) {
        this.exportPresentationAction = exportPresentationAction;
    }
}
