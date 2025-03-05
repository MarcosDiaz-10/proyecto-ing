import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestControladorIniciarSesion {
    @Test
    public void testLoginFirstName() {
        //Arrange
        IniciarSesion instanceTestLoginFirstName = new IniciarSesion();
        ControladorIniciarSesion instanceTestControllerFirstName = new ControladorIniciarSesion();
        instanceTestLoginFirstName.setFirstName("TestFirstName");
        instanceTestControllerFirstName.validateInformation(instanceTestLoginFirstName);
        //Act
        String testResult = instanceTestControllerFirstName.getFirstName();
        //Assert
        assertThat (testResult, is("TestFirstName"));
    }
    @Test
    public void testLoginLastName() {
        //Arrange
        IniciarSesion instanceTestLoginLastName = new IniciarSesion();
        ControladorIniciarSesion instanceTestControllerLastName = new ControladorIniciarSesion();
        instanceTestLoginLastName.setLastName("TestLastName");
        instanceTestControllerLastName.validateInformation(instanceTestLoginLastName);
        //Act
        String testResult = instanceTestControllerLastName.getLastName();
        //Assert
        assertThat (testResult, is ("TestLastName"));
    }
    @Test
    public void testLoginPassword() {
        //Arrange
        IniciarSesion instanceTestLoginPassword = new IniciarSesion();
        ControladorIniciarSesion instanceTestControllerLoginPassword = new ControladorIniciarSesion();
        instanceTestLoginPassword.setPassword("TestPassword");
        instanceTestControllerLoginPassword.validateInformation(instanceTestLoginPassword);
        //Act
        String testResult = instanceTestControllerLoginPassword.getPassword();
        //Assert
        assertThat (testResult, is ("TestPassword"));
    }
    @Test
    public void testUnexistentUser() {
        //Arrange
        IniciarSesion instanceTestLoginUnexistentUser = new IniciarSesion();
        ControladorIniciarSesion instanceTestControllerUnexistentUser = new ControladorIniciarSesion();
        instanceTestLoginUnexistentUser.setFirstName("UsuarioInexistente");
        instanceTestLoginUnexistentUser.setLastName("UsuarioInexistente");
        instanceTestLoginUnexistentUser.setPassword("UsuarioInexistente");
        //Act
        int testResult = instanceTestControllerUnexistentUser.validateInformation(instanceTestLoginUnexistentUser);
        //Assert
        assertThat (testResult, is ((int) 4));
    }
    @Test
    public void testExistentUser() {
        //Arrange
        IniciarSesion instanceTestLoginExistentUser = new IniciarSesion();
        ControladorIniciarSesion instanceTestControllerExistentUser = new ControladorIniciarSesion();
        instanceTestLoginExistentUser.setFirstName("admin");
        instanceTestLoginExistentUser.setLastName("admin");
        instanceTestLoginExistentUser.setPassword("123");
        //Act
        int testResult = instanceTestControllerExistentUser.validateInformation(instanceTestLoginExistentUser);
        //Assert
        assertThat (testResult, is (0));
    }
}

/* Codigos error para Iniciar Sesion
 * 0 no hay errores
 * 1 error con nombre
 * 2 error con apellido
 * 3 error con contraseña
 * 4 no existe el usuario
 * -1 no existe base de dato
 */