/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import db.histories.FlexNote;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexNoteForm extends FormLayout {

    private FlexNote note;
    private TextField titleField;
    private TextArea contentArea;
    private FlexButton saveButton;
    
    public FlexNoteForm(FlexNote flexNote) {
        this.note = flexNote;
        initialize();
        setSizeFull();
        setSpacing(true);
        setMargin(true);
        addComponents(titleField, contentArea, saveButton);
    }
    
    private void initialize() {
        if(note.getTitle() != null) {
            this.titleField = new TextField("Title", note.getTitle());
        }
        else {
            this.titleField = new TextField("Title");
        }            
        titleField.setSizeFull();
        
        if(note.getContent() != null) {
            this.contentArea = new TextArea("Content", note.getContent());
        }

        else {
            this.contentArea = new TextArea("Content");
        }
        contentArea.setSizeFull();
        
        saveButton = new SaveButton();
        saveButton.addClickListener(event -> {
            ServiceLocator.getInstance().findNotesService().save(note);
            ((Window)getParent()).close();
        });
        saveButton.setSizeUndefined();
                
        
        
    }
    
}
