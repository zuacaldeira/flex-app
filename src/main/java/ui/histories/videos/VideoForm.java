/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.histories.videos;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import ui.SaveButton;
import java.util.Locale;

/**
 *
 * @author zua
 */
public class VideoForm extends FormLayout {

    private TextField urlField;
    private TextField titleField;
    private TextField authorField;
    private TextArea descriptionField;
    private ComboBox languageField;
    private SaveButton saveButton;
    
    public VideoForm() {
        addTitleField();
        addUrlField();
        addAuthorField();
        addDescriptionField();
        addLanguageField();
        addSaveButton();
        HorizontalLayout authorAndLanguage = new HorizontalLayout(authorField, languageField);
        authorAndLanguage.setWidth("100%");
        addComponents(urlField, titleField, authorAndLanguage,  descriptionField, saveButton);
        setMargin(true);
    }
    
    private void  addTitleField() {
        titleField = new TextField("Title");
        titleField.setSizeFull();
        titleField.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    }
    
    private void  addUrlField() {
        urlField = new TextField("Url");
        urlField.setSizeFull();
        urlField.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    }

    private void  addLanguageField() {
        languageField = new ComboBox("Language");
        languageField.setItems(Locale.getISOLanguages());             
        languageField.setSizeFull();
        languageField.setStyleName(ValoTheme.COMBOBOX_ALIGN_CENTER);
    }

    private void  addDescriptionField() {
        descriptionField = new TextArea("Video Description");
        descriptionField.setSizeFull();
        descriptionField.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    }

    private void  addAuthorField() {
        authorField = new TextField("Author");
        authorField.setSizeFull();
        authorField.setStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
    }

    
    private void addSaveButton() {
        saveButton = new SaveButton();
    }

    public TextField getUrlField() {
        return urlField;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getAuthorField() {
        return authorField;
    }

    public ComboBox getLanguageField() {
        return languageField;
    }

    public SaveButton getSaveButton() {
        return saveButton;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }
    
    
    
    
}
