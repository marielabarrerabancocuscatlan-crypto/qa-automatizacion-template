package automatizacion;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EX001ComprarUserAutenticado {
     WebDriver driver;

    //Acciones que se van a ejecutar antes de cada test
        @BeforeEach 
        public void inicializarNavegador(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();

 } 

  @Test 
    public void loginExitoso(){
       driver.get("https://teststore.automationtesting.co.uk/index.php");
       driver.findElement(By.className("user-info")).click();;
       //Ingresar las credenciales de autenticación
       driver.findElement(By.id("field-email")).sendKeys("mariela@yopmail.com");
       driver.findElement(By.id("field-password")).sendKeys("Prueba123!");
       
       driver.findElement(By.id("submit-login")).click();

       //Validación de URL
       String urlEsperada = "https://teststore.automationtesting.co.uk/index.php";
       
       
       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

        //Validación de mensaje exitoso

        WebElement mensajeLoginExitoso = driver.findElement(By.className("hidden-sm-down"));
        String textoMensaje = mensajeLoginExitoso.getText();

        //Uso condicional para validar login exitoso
        if(textoMensaje.contains("Mariela Barrera")){
            System.out.println("Login exitoso validado correctamente");
        }else{
            System.out.println("Error en el mensaje de login encontrado");

            Assertions.fail("Se esperaba el mensaje: Thank you for your mail!"
                + "\n Pero se encontró:"
                + textoMensaje);
        }

    }

    @Test 
    public void agregarProductos(){
 driver.get("https://teststore.automationtesting.co.uk/index.php");
       //Seleccionar categoria de busqueda 
       driver.get("https://teststore.automationtesting.co.uk/index.php");
       driver.findElement(By.className("user-info")).click();;
       //Ingresar las credenciales de autenticación
       driver.findElement(By.id("field-email")).sendKeys("mariela@yopmail.com");
       driver.findElement(By.id("field-password")).sendKeys("Prueba123!");
       driver.findElement(By.id("submit-login")).click();

       // Navegación en catalogo de productos
       driver.findElement(By.id("category-9")).click();

       //Validación de URL
        String urlEsperada = "https://teststore.automationtesting.co.uk/index.php?id_category=9&controller=category";
       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

       //Agregar productos en carrito

    driver.findElements(By.cssSelector("a.product-thumbnail"))
      .get(0)
      .click();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

WebElement btnAddToCart = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("button[data-button-action='add-to-cart']")
    )
);
btnAddToCart.click();

          //Confirmar en el modal de checkout
      
        WebElement btnCheckout = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'btn') and contains(.,'Proceed to checkout')]")
        ));

btnCheckout.click();
// Confirmar la compra

 WebElement btnFinalizar = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'btn') and contains(.,'Proceed to checkout')]")
        ));

btnFinalizar.click();

// Confirmar Informacion

WebElement btnContinueAddress = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.name("confirm-addresses")
    )
);

btnContinueAddress.click();



WebElement btnContinueShipping = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.name("confirmDeliveryOption")
    )
);

WebElement paymentMethod = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.id("payment-option-1")
    )
);

paymentMethod.click();

WebElement chkTerms = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.id("conditions_to_approve[terms-and-conditions]")
    )
);

chkTerms.click();

btnContinueShipping.click();

    }  

    
}