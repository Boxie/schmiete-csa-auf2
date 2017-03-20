package com.jsonsurfer.gui.aufgaben;

/**
 * Created by Lukas on 14.03.17.
 */
import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class Console extends OutputStream
{
    private TextArea output;

    public Console(TextArea ta)
    {
        this.output = ta;
    }

    /**
     * Convert int to char and print it to console
     * @param i int ascii
     * @throws IOException
     */
    @Override
    public void write(int i) throws IOException
    {
        javafx.application.Platform.runLater(() -> output.appendText(String.valueOf((char) i)));
    }

    /**
     * Write line in console
     * @param text string message
     */
    public void writeln(String text){
    	javafx.application.Platform.runLater(() ->output.appendText("\n " + text));
    }

    /**
     * Clear Console Text
     */
    public void clear(){
        output.setText("");
    }

}
