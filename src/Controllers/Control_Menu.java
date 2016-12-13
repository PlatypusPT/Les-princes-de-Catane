package Controllers;

import Models.Joueur;
import Models.Model;
import Models.Partie;
import Views.Menu_View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

/**
 * Created by yhaffner on 21/11/16.
 */
public class Control_Menu implements EventHandler<ActionEvent>{
    private Menu_View view;
    private Model model;

    private String j1,j2;
    private int c1,c2;

    public Control_Menu(Model model, Menu_View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    @Override
    public void handle(ActionEvent event) {
        int i=0;
        System.out.println("Button clicked: "+event.getSource());

        if(view.startButton.equals(event.getSource())) view.setWidgetAskFirstPlayer();

        if(view.continueButton.equals(event.getSource())) {
            if(view.jnomTextField.getText().replaceAll(" ", "").length() == 0) j1 = "J1";
            else j1 = view.jnomTextField.getText();
            for (RadioButton rb:view.blasonsIMGJ) if(rb.isSelected()) c1=i; else i++;
            view.blasonsIMGJ.get(c1).setDisable(true);
            view.setWidgetAskSecondPlayer();

        }

        if(view.realStartButton.equals(event.getSource())) {
            if(view.jnomTextField.getText().replaceAll(" ", "").length() == 0) j2 = "J2";
            else j2 = view.jnomTextField.getText();
            for (RadioButton rb:view.blasonsIMGJ) if(rb.isSelected()) c2=i; else i++;

            view.startGame(new Partie(new Joueur(j1,c1),new Joueur(j2,c2)));
        }
    }
}
