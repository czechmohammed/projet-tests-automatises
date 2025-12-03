# Tests Java Selenium - TodoMVC

Deuxieme approche pour tester TodoMVC, cette fois avec Java et Selenium WebDriver.

## Technologies

- Java 21
- Selenium WebDriver 4.15
- TestNG 7.8
- Maven
- Pattern Page Object

## Structure

Le projet utilise le pattern Page Object Model pour separer la logique des tests de l'interaction avec la page.
```
java-selenium/
├── src/
│   ├── main/java/pages/
│   │   └── TodoMVCPage.java      # classe page object
│   └── test/java/tests/
│       └── TodoMVCTest.java       # tests TestNG
├── pom.xml
└── README.md
```

## Installation

Les dependances sont gerees par Maven. Pour les telecharger:
```bash
mvn clean install
```

## Lancer les tests
```bash
mvn test
```

Les tests tournent en mode headless (sans interface graphique).

## Tests implementes

- test01: ajouter une tache simple
- test02: ajouter plusieurs taches
- test03: cocher une tache pour la marquer terminee
- test04: supprimer une tache
- test05: filtrer les taches actives
- test06: filtrer les taches completees

## Resultats

6 tests qui passent tous

## Comparaison Robot Framework vs Java Selenium

**Robot Framework**:
- syntaxe simple et lisible
- rapide a mettre en place
- bon pour des tests simples

**Java Selenium**:
- plus de controle sur le code
- meilleure integration dans des projets Java
- pattern Page Object plus propre pour gros projets
- plus verbose mais plus flexible
