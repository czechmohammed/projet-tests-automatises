# Projet de tests automatises TodoMVC

Projet personnel d'apprentissage des tests automatises avec Robot Framework et Java Selenium.

## Contexte

Ce projet a ete realise pour me preparer a un stage en test logiciel chez Sogeti Capgemini.
L'objectif etait de comprendre et pratiquer l'automatisation de tests sur une vraie application web.

## Application testee

TodoMVC (React): https://todomvc.com/examples/react/dist/

Une application simple de gestion de taches qui permet de:
- Ajouter des taches
- Les marquer comme terminees
- Les supprimer
- Les filtrer (All, Active, Completed)

## Technologies utilisees

### Projet 1: Robot Framework
- Robot Framework 7.3.2
- SeleniumLibrary 6.8.0
- Python 3.13
- Firefox 145

### Projet 2: Java Selenium (en cours)
- Java 17
- Selenium WebDriver
- TestNG
- Maven

## Structure du projet
```
projet-tests-automatises/
├── tests/                          # Tests automatises Robot Framework
│   ├── test_ajout_tache.robot
│   ├── test_modification_tache.robot
│   └── test_filtrage_taches.robot
├── ressources/
│   └── keywords.robot              # Keywords reutilisables
├── rapports/                       # Rapports de tests generes
├── docs/
│   ├── plan_de_tests.md           # Plan de tests detaille
│   └── strategie_recette.md       # Strategie globale
└── README.md
```

## Installation

### Prerequis
- Python 3.x
- pip
- Firefox ou Chrome

### Installation des dependances
```bash
pip3 install robotframework
pip3 install robotframework-seleniumlibrary
pip3 install webdriver-manager
```

## Lancement des tests

### Tous les tests
```bash
robot tests/
```

### Un fichier specifique
```bash
robot tests/test_ajout_tache.robot
```

### Par tag
```bash
robot --include ajout tests/
robot --include priorite_haute tests/
```

## Resultats

8 cas de test automatises couvrant:
- Ajout de taches (3 tests)
- Modification et suppression (2 tests)
- Filtrage (3 tests)

Taux de reussite actuel: 100% (8/8)

## Rapports

Les rapports sont generes automatiquement apres chaque execution:
- `report.html`: vue d'ensemble visuelle
- `log.html`: details de chaque etape
- `output.xml`: donnees brutes

## Documentation

- [Plan de tests](docs/plan_de_tests.md): description detaillee des cas de test
- [Strategie de recette](docs/strategie_recette.md): approche globale et processus

## Prochaines etapes

- [ ] Integration Jenkins pour CI/CD
- [ ] Projet parallele avec Java + Selenium
- [ ] Tests de performance avec Locust
- [ ] Dashboard de suivi des tests

## Auteur

Mohammed Mekdour 
Etudiant M2 Cloud et Infrastructures
ENSIMAG
