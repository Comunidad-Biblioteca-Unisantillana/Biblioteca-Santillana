package general.vista;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Clase que se encarga de iniciar un menu desplegable.
 *
 * @author Julian
 * @creado 18/07/2019
 * @autor Miguel Fernández
 * @modificado 21/08/2019
 */
public class IniciarMenuDesplegable {

    private HamburgerBackArrowBasicTransition burgerTask2;
    private JFXDrawer drawer;

    /**
     * Método que se encarga de cargar los componentes de un menu desplegable
     *
     * @param drawer
     * @param anchorDrawer
     * @param hamburger
     */
    public IniciarMenuDesplegable(JFXDrawer drawer, AnchorPane anchorDrawer, JFXHamburger hamburger) {
        this.drawer = drawer;

        drawer.setSidePane(anchorDrawer);
        burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    /**
     * el metódo repliega la barra del menu.
     */
    public void valorPorDefecto() {
        if (drawer.isShown()) {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            drawer.close();
        }
    }
}
