package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.button.Button;

public class SeatButton extends Button {
    public static final String DISABLED_BACKGROUND_COLOR = "#808080";
    public static final String UNCHECKED_BACKGROUND_COLOR = "#00FF00";
    public static final String CHECKED_BACKGROUND_COLOR = "#FFFF00";
    private boolean value = false;
    public SeatButton(String text){
        getStyle().set("background-color", UNCHECKED_BACKGROUND_COLOR);
        setText(text);
    }
    public void changeValue() {
        value = !value;
        if (this.value) {
            this.getStyle().set("background-color", UNCHECKED_BACKGROUND_COLOR);
        }
        else {
            this.getStyle().set("background-color", CHECKED_BACKGROUND_COLOR);
        }
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}

