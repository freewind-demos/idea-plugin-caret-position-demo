package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.impl.ProjectImpl
import com.intellij.openapi.ui.Messages

class MoveCaretToEndAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        (event.project as? ProjectImpl)?.let { project ->
            val editor = FileEditorManager.getInstance(project)?.selectedEditors?.filterIsInstance<TextEditor>()?.firstOrNull()
            if (editor != null) {
                val offset = editor.editor.document.textLength
                ApplicationManager.getApplication().runWriteAction {
                    editor.editor.caretModel.moveToOffset(offset)
                }
            } else {
                Messages.showWarningDialog("Please open a text file", "Can't work")
            }
        }
    }

}