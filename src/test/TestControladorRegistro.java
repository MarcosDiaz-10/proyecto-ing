import org.junit.Test;

import controllers.ControladorRegistrarse;
import views.Registrarse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestControladorRegistro {
    @Test
    public void testFirstName() {
        // Arrange
        Registrarse instanceTestRegisterFirstName = new Registrarse();
        ControladorRegistrarse instanceTestControllerFirstName = new ControladorRegistrarse();
        instanceTestRegisterFirstName.setFirstName("PruebaFirstName");
        instanceTestControllerFirstName.saveInformation(instanceTestRegisterFirstName);
        // Act
        String testResult = instanceTestRegisterFirstName.getFirstName();
        // Assert
        assertThat(testResult, is("PruebaFirstName"));
    }

    @Test
    public void testLastName() {
        // Arrange
        Registrarse instanceTestTegisterLastName = new Registrarse();
        ControladorRegistrarse instanceTestControllerLastName = new ControladorRegistrarse();
        instanceTestTegisterLastName.setLastName("PruebaLastName");
        instanceTestControllerLastName.saveInformation(instanceTestTegisterLastName);
        // Act
        String testResult = instanceTestControllerLastName.getLastName();
        // Assert
        assertThat(testResult, is("PruebaLastName"));
    }

    @Test
    public void testRole() {
        // Arrange
        Registrarse instanceTestRegisterRol = new Registrarse();
        ControladorRegistrarse instanceTestControllerRol = new ControladorRegistrarse();
        instanceTestRegisterRol.setRol("Estudiante");
        instanceTestControllerRol.saveInformation(instanceTestRegisterRol);
        // Act
        String testResult = instanceTestControllerRol.getRol();
        // Assert
        assertThat(testResult, is("Estudiante"));
    }

    @Test
    public void testPassword() {
        // Arrange
        Registrarse instanceTestRegisterPassword = new Registrarse();
        ControladorRegistrarse instanceTestControllerPassword = new ControladorRegistrarse();
        instanceTestRegisterPassword.setPassword("PruebaPassword");
        instanceTestControllerPassword.saveInformation(instanceTestRegisterPassword);
        // Act
        String testResult = instanceTestControllerPassword.getPassword();
        // Assert
        assertThat(testResult, is("PruebaPassword"));
    }

    @Test
    public void testRepeatPassword() {
        // Arrange
        Registrarse instanceTestRegisterRepeatPassword = new Registrarse();
        ControladorRegistrarse instanceTestControllerRepeatPassword = new ControladorRegistrarse();
        instanceTestRegisterRepeatPassword.setRepeatPassword("PruebaRepeatassword");
        instanceTestControllerRepeatPassword.saveInformation(instanceTestRegisterRepeatPassword);
        // Act
        String testResult = instanceTestControllerRepeatPassword.getRepeatPassword();
        // Assert
        assertThat(testResult, is("PruebaRepeatassword"));
    }

    @Test
    public void testRegister() {
        // Arrange
        Registrarse instanceTestRegisterMatchPasswords = new Registrarse();
        ControladorRegistrarse instanceTestControllerMatchPasswords = new ControladorRegistrarse();
        instanceTestRegisterMatchPasswords.setFirstName("test");
        instanceTestRegisterMatchPasswords.setLastName("test");
        instanceTestRegisterMatchPasswords.setSchool("Biolog\u00EDa");
        instanceTestRegisterMatchPasswords.setRol("Estudiante");
        instanceTestRegisterMatchPasswords.setPassword("test");
        instanceTestRegisterMatchPasswords.setRepeatPassword("test");

        // Act
        int testResult = instanceTestControllerMatchPasswords.saveInformation(instanceTestRegisterMatchPasswords);
        // Assert
        assertThat(testResult, is(0));
    }
}

/*
 * Codigos error
 * 0 = No hay error
 * 1 = Nombre vacio
 * 2 = Apellido vacio
 * 3 = Rol no identificado
 * 4 = Contraseña vacia
 * 5 = Contraseña no es igual
 * -1 = Error de escritura?
 */
