package integration;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import controllers.IController;

public abstract class BaseControllerTest<T> {

    protected IController<T> controller;
    protected List<T> estadoInicial;

    @BeforeEach
    void setUp() {
        controller = createController();
        estadoInicial = new ArrayList<>(controller.listar());
    }

    @AfterEach
    void tearDown() {
        List<T> estadoAtual = new ArrayList<>(controller.listar());
        for (T item : estadoAtual) {
            if (!estadoInicial.contains(item)) {
                controller.deletar(item);
            }
        }
    }
    //a diferença dessa classe para suas implementações se dá nesse método abaixo
    protected abstract IController<T> createController();
}