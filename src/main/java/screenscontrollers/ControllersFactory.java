package screenscontrollers;
import controllers.SecaoController;
import controllers.SubeventoController;
import controllers.TrilhaController;
import controllers.EventoController;

public interface ControllersFactory {
    SecaoController createSecaoController();
    EventoController createEventoController();
    SubeventoController createSubeventoController();
    TrilhaController createTrilhaController();
}