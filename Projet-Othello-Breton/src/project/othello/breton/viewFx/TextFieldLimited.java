package project.othello.breton.viewFx;

import javafx.scene.control.TextField;

/**
 * This class provides methods to create a textfield with limit the length of 
 * its inputs.
 * 
 * @author Gabriel Breton - 43397
 */
class TextFieldLimited extends TextField {  
    private int maxlength;
    
    /**
     * Creates a new limited textfield.
     */
    TextFieldLimited() {
        this.maxlength = 10;
    }
    
    /**
     * Sets the maximal length of the input on the textfield.
     * 
     * @param maxlength the maximal length of the input.
     */
    void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }
    
   /**
     * Replaces a range of characters with the given text.
     *
     * @param start the starting index in the range, inclusive. 
     * @param end the ending index in the range, exclusive. 
     * @param text the text that is to replace the range. This must not be null.
     */
    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxlength) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * Replaces the selection with the given replacement String. If there is
     * no selection, then the replacement text is simply inserted at the current
     * caret position. If there was a selection, then the selection is cleared
     * and the given replacement text inserted.
     * 
     * @param the replacement String.
     */
    @Override
    public void replaceSelection(String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxlength) {
            // Add characters, but don't exceed maxlength.
            if (text.length() > maxlength - getText().length()) {
                text = text.substring(0, maxlength- getText().length());
            }
            super.replaceSelection(text);
        }
    }
    
    
}
