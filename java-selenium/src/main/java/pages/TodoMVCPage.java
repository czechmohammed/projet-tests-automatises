package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class TodoMVCPage {

    WebDriver driver;
    WebDriverWait wait;

    // elements de la page
    By champTexte = By.cssSelector(".new-todo");
    By listeTaches = By.cssSelector(".todo-list li");
    By compteur = By.cssSelector(".todo-count");
    By filtreAll = By.xpath("//a[contains(text(),'All')]");
    By filtreActive = By.xpath("//a[contains(text(),'Active')]");
    By filtreCompleted = By.xpath("//a[contains(text(),'Completed')]");

    public TodoMVCPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void ouvrirPage() {
        driver.get("https://todomvc.com/examples/react/dist/");
        wait.until(ExpectedConditions.presenceOfElementLocated(champTexte));
    }

    // ajouter une tache
    public void ajouterTache(String texte) {
        WebElement champ = driver.findElement(champTexte);
        champ.sendKeys(texte);
        champ.sendKeys(Keys.RETURN);
        try {
            Thread.sleep(500); // attendre que l'UI se mette a jour
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // verifier si une tache est affichee
    public boolean tacheEstVisible(String texte) {
        return driver.getPageSource().contains(texte);
    }

    // recuperer le texte du compteur
    public String getCompteur() {
        try {
            return driver.findElement(compteur).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // compter le nombre de taches
    public int getNombreTaches() {
        return driver.findElements(listeTaches).size();
    }

    // cocher une tache pour la marquer comme terminee
    public void cocherTache(int index) {
        List<WebElement> taches = driver.findElements(listeTaches);
        WebElement checkbox = taches.get(index).findElement(By.cssSelector(".toggle"));
        checkbox.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // supprimer une tache
    public void supprimerTache(int index) {
        List<WebElement> taches = driver.findElements(listeTaches);
        WebElement tache = taches.get(index);

        // il faut passer la souris sur la tache pour voir le bouton X
        Actions actions = new Actions(driver);
        actions.moveToElement(tache.findElement(By.cssSelector("label"))).perform();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement boutonX = tache.findElement(By.cssSelector(".destroy"));
        boutonX.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cliquerFiltreActive() {
        driver.findElement(filtreActive).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cliquerFiltreCompleted() {
        driver.findElement(filtreCompleted).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cliquerFiltreAll() {
        driver.findElement(filtreAll).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
