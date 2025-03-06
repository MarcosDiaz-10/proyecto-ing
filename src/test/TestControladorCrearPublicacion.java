import org.junit.Test;

import controllers.ControladorCrearPublicacion;
import views.CrearPublicacion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestControladorCrearPublicacion {
    @Test
    public void testTitleCrearPublicacion() {
        // Arrange
        CrearPublicacion instanceTestCrearPublicacion = new CrearPublicacion();
        ControladorCrearPublicacion instanceTestControladorCrearPublicacion = new ControladorCrearPublicacion();
        instanceTestCrearPublicacion.setTitle("");
        // Act
        int testResult = instanceTestControladorCrearPublicacion
                .saveInformationPublication(instanceTestCrearPublicacion, "");
        // Assert
        assertThat(testResult, is(1));
    }

    @Test
    public void testDescriptionCrearPublicacion() {
        // Arrange
        CrearPublicacion instanceTestCrearPublicacion = new CrearPublicacion();
        ControladorCrearPublicacion instanceTestControladorCrearPublicacion = new ControladorCrearPublicacion();
        instanceTestCrearPublicacion.setTitle("Title");
        instanceTestCrearPublicacion.setInterstTypeValue("InterstTypeValue");
        instanceTestCrearPublicacion.setPostTypeValue("Taller");
        instanceTestCrearPublicacion.setDescription("");
        // Act
        int testResult = instanceTestControladorCrearPublicacion
                .saveInformationPublication(instanceTestCrearPublicacion, "");
        // Assert
        assertThat(testResult, is(4));
    }
}

/*
 * Pruebas
 * 0. No hay problemas
 * 1. Titulo no existe
 * 2. Tipo de interes no existe
 * 3. Tipo de publicacion no existe
 * 4. Descripcion no existe
 */
