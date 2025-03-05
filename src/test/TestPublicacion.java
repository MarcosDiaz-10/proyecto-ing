import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;

import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestPublicacion {

    @Test
    public void testClasificacion(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));

            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getClassification(), is("TestTipo"));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testTitulo(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getTitle(), is("TestTitulo"));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testTexto(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent accumsan lacinia ante, in consectetur sem tincidunt vitae. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec finibus, odio id placerat porttitor, nisl magna egestas lorem, at hendrerit quam massa sed mauris. In eu sapien ultricies, eleifend lectus eget, semper eros. Fusce ac ultrices purus. In consectetur dolor sit amet ligula pellentesque, quis dignissim velit pharetra. Duis hendrerit hendrerit congue. Nunc ut massa eget ante maximus lacinia. Donec lacus dolor, efficitur eget lorem ac, porta placerat leo. Aliquam blandit augue erat, varius eleifend turpis euismod eget. Nunc non enim id massa sollicitudin ultricies. Donec sed tincidunt dolor. Vivamus lobortis, dui in efficitur scelerisque, velit purus euismod dolor, vel tempor lacus turpis ac risus. Sed lacus massa, tempus sit amet eros eu, sollicitudin imperdiet tellus. Sed tincidunt venenatis magna, et porta dolor posuere nec.";
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getText(), is(texto));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testImagen(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.hasImage(), is(false));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());        
        }
    }
    @Test
    public void testDueno(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getOwner(), is("TestNombre"));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testDia(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getDay(), is(1));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testMes(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getMonth(), is(6));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testAno(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getYear(), is(2000));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
    @Test
    public void testFecha(){
        try {
            //Arrange
            BufferedReader reader = new BufferedReader(new FileReader("src/test/testPostDatabase.txt"));
            //Act
            Publicacion testPublicacion = new Publicacion(reader);
            //Assert
            assertThat(testPublicacion.getDate(), is("1-6-2000"));
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }
}


/* Cómo es la vuelta:
 * Creamos los parametros de un texto, simulando una publicación creada
 * Pasamos ese texto a Publicacion dentro de un Buffered Reader
 * Creamos metodos que chequeen que cada atributo de Publicacion es correspondido por el elemento del texto descrito
 * Y ya
 * 
 * 
 * Parametros a testear:
 * Clasificacion
 * Titulo
 * Texto
 * si tiene imagen
 * Dueno
 * Dia
 * Mes
 * Ano
 * Fecha
 * 
 * 
 * 
 */