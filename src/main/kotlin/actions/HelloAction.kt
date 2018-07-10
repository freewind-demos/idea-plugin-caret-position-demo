package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.impl.ProjectImpl
import com.intellij.openapi.ui.Messages

class HelloAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        (event.project as? ProjectImpl)?.let { project ->
            val editor = FileEditorManager.getInstance(project)?.selectedEditors?.mapNotNull { it as? TextEditor }?.firstOrNull()
            if (editor != null) {
                val offset = editor.editor.caretModel.offset
                Messages.showInfoMessage("Caret position: $offset", "Caret Position")
            } else {
                Messages.showWarningDialog("Please open a text file", "Can't work")
            }
        }
    }

}