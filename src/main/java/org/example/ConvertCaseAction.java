package org.example;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class ConvertCaseAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            return;
        }

        Document document = editor.getDocument();
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText == null || selectedText.isEmpty()) {
            Messages.showInfoMessage("Сначала выделите текст в редакторе!", "TextHelper");
            return;
        }

        int count = selectedText.length();
        String upperText = selectedText.toUpperCase();

        WriteCommandAction.runWriteCommandAction(editor.getProject(), () -> {
            int start = editor.getSelectionModel().getSelectionStart();
            int end = editor.getSelectionModel().getSelectionEnd();
            document.replaceString(start, end, upperText);
        });

        Messages.showInfoMessage(
                "Текст переведён в UPPERCASE!\nКоличество символов: " + count,
                "TextHelper (Сосин Артём M3115)"
        );
    }
}
// HELLO ITMO UNIVERSITY