import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class V2TestControladorRegistro {
    @Test
    public void testFirstName(){
        //Arrange
        Registrarse instanceTestRegisterFirstName = new Registrarse();
        instanceTestRegisterFirstName.setFirstName("PruebaFirstName");
        //Act
        String testResult = instanceTestRegisterFirstName.getFirstName();
        //Assert
        assertThat (testResult, is("PruebaFirstName"));
    }
    @Test
    public void testLastName(){
        //Arrange
        Registrarse instanceTestTegisterLastName = new Registrarse();
        instanceTestTegisterLastName.setLastName("PruebaLastName");
        //Act
        String testResult = instanceTestTegisterLastName.getLastName();
        //Assert
        assertThat (testResult, is ("PruebaLastName"));
    }
    @Test
    public void testRole(){
        //Arrange
        Registrarse instanceTestRegisterRol = new Registrarse();
        instanceTestRegisterRol.setRol("Estudiante");
        //Act
        String testResult = instanceTestRegisterRol.getSelectedRole();
        //Assert
        assertThat (testResult, is ("Estudiante"));
    }
    @Test
    public void testPassword(){
        //Arrange
        Registrarse instanceTestRegisterPassword = new Registrarse();
        instanceTestRegisterPassword.setPassword("PruebaPassword");
        //Act
        String testResult = instanceTestRegisterPassword.getPassword();
        //Assert
        assertThat (testResult, is ("PruebaPassword"));
    }
    public void testRepeatPassword(){
        //Arrange
        Registrarse instanceTestRegisterRepeatPassword = new Registrarse();
        instanceTestRegisterRepeatPassword.setRepeatPassword("PruebaRepeatassword");
        //Act
        String testResult = instanceTestRegisterRepeatPassword.getRepeatPassword();
        //Assert
        assertThat (testResult, is ("PruebaRepeatassword"));
    }
    @Test
    public void testPasswordMatch(){
        //Arrange
        Registrarse instanceTestRegisterMatchPasswords = new Registrarse();
        instanceTestRegisterMatchPasswords.setPassword("TestMatchPasswords");
        instanceTestRegisterMatchPasswords.setRepeatPassword("TestMatchPasswords");
        //Act
        boolean testResult = instanceTestRegisterMatchPasswords.getPassword().equals(instanceTestRegisterMatchPasswords.getRepeatPassword());
        //Assert
        assertThat (testResult, is ((true)));
    }
}

/* Codigos error
 * 0 = No hay error
 * 1 = Nombre vacio
 * 2 = Apellido vacio
 * 3 = Rol no identificado
 * 4 = Contraseña vacia
 * 5 = Contraseña no es igual
 * -1 = Error de escritura?
 */
