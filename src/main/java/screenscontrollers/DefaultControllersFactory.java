package screenscontrollers;

import controllers.SecaoController;
import controllers.SubeventoController;
import controllers.TrilhaController;
import controllers.EventoController;

public class DefaultControllersFactory implements ControllersFactory {
    @Override
    public SecaoController createSecaoController() {
        return new SecaoController();
    }

    @Override
    public EventoController createEventoController() {
        return new EventoController();
    }

    @Override
    public SubeventoController createSubeventoController() {
        return new SubeventoController();
    }

    @Override
    public TrilhaController createTrilhaController() {
        return new TrilhaController();
    }
}