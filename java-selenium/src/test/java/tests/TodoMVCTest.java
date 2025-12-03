package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TodoMVCPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TodoMVCTest {

    WebDriver driver;
    TodoMVCPage page;

    @BeforeMethod
    public void setup() {
        // configurer le driver firefox
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // mode sans interface pour CI

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        page = new TodoMVCPage(driver);
    }

    @AfterMethod
    public void fermer() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test01_ajouterUneTache() {
        page.ouvrirPage();
        page.ajouterTache("Acheter du pain");

        Assert.assertTrue(page.tacheEstVisible("Acheter du pain"));
        Assert.assertTrue(page.getCompteur().contains("1 item left"));
    }

    @Test
    public void test02_ajouterPlusieursTaches() {
        page.ouvrirPage();
        page.ajouterTache("Tache 1");
        page.ajouterTache("Tache 2");
        page.ajouterTache("Tache 3");

        Assert.assertTrue(page.tacheEstVisible("Tache 1"));
        Assert.assertTrue(page.tacheEstVisible("Tache 2"));
        Assert.assertTrue(page.tacheEstVisible("Tache 3"));
        Assert.assertTrue(page.getCompteur().contains("3 items left"));
    }

    @Test
    public void test03_cocherUneTache() {
        page.ouvrirPage();
        page.ajouterTache("Faire les courses");
        page.cocherTache(0);

        // apres avoir coche, le compteur doit afficher 0
        Assert.assertTrue(page.getCompteur().contains("0 items left"));
    }

    @Test
    public void test04_supprimerUneTache() {
        page.ouvrirPage();
        page.ajouterTache("A supprimer");

        // verifier qu'elle est la
        Assert.assertTrue(page.tacheEstVisible("A supprimer"));

        page.supprimerTache(0);

        // verifier qu'il n'y a plus de taches
        Assert.assertEquals(page.getNombreTaches(), 0);
    }

    @Test
    public void test05_filtrerTachesActives() {
        page.ouvrirPage();
        page.ajouterTache("Tache active 1");
        page.ajouterTache("Tache active 2");
        page.ajouterTache("Tache completee");

        // cocher la derniere
        page.cocherTache(2);

        // cliquer sur filtre active
        page.cliquerFiltreActive();

        // seules les taches actives doivent etre visibles
        Assert.assertTrue(page.tacheEstVisible("Tache active 1"));
        Assert.assertTrue(page.tacheEstVisible("Tache active 2"));
    }

    @Test
    public void test06_filtrerTachesCompletees() {
        page.ouvrirPage();
        page.ajouterTache("Tache active");
        page.ajouterTache("Tache completee");

        // cocher la deuxieme
        page.cocherTache(1);

        // cliquer sur filtre completed
        page.cliquerFiltreCompleted();

        // seule la tache completee doit etre visible
        Assert.assertTrue(page.tacheEstVisible("Tache completee"));
    }
}
