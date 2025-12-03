# Projet de tests automatises TodoMVC

Projet personnel pour apprendre les tests automatises avec Robot Framework et Java Selenium.

## Contexte

Ce projet a ete fait pour me preparer a un stage en test logiciel chez Sogeti Capgemini.
L'objectif etait de pratiquer l'automatisation de tests sur une vraie application web avec deux approches differentes.

## Application testee

TodoMVC (React): https://todomvc.com/examples/react/dist/

Application simple de gestion de taches:
- Ajouter des taches
- Les marquer comme terminees
- Les supprimer
- Les filtrer (All, Active, Completed)

## Technologies utilisees

### Projet 1: Robot Framework
- Robot Framework 7.3.2
- SeleniumLibrary 6.8.0
- Python 3.13
- Firefox ESR 140
- Jenkins (Docker)
- ngrok pour exposition publique

### Projet 2: Java Selenium
- Java 21
- Selenium WebDriver 4.15
- TestNG 7.8
- Maven
- Pattern Page Object Model

## Structure du projet
```
projet-tests-automatises/
├── tests/                          # tests Robot Framework
│   ├── test_ajout_tache.robot
│   ├── test_modification_tache.robot
│   └── test_filtrage_taches.robot
├── ressources/
│   └── keywords.robot              # keywords reutilisables
├── java-selenium/                  # projet Java Selenium
│   ├── src/main/java/pages/
│   └── src/test/java/tests/
├── docs/
│   ├── plan_de_tests.md
│   ├── strategie_recette.md
│   └── jenkins_setup.md
├── Dockerfile                      # image Jenkins personnalisee
└── README.md
```

## Installation

### Prerequis
- Python 3.x et pip
- Java 21 et Maven
- Firefox
- Docker pour Jenkins

### Dependances Python
```bash
pip3 install -r requirements.txt
```

### Dependances Java
```bash
cd java-selenium
mvn clean install
```

## Lancer les tests

### Tests Robot Framework

Tous les tests:
```bash
robot tests/
```

Un fichier specifique:
```bash
robot tests/test_ajout_tache.robot
```

Par tag:
```bash
robot --include ajout tests/
```

### Tests Java Selenium

Voir le README dedie: [java-selenium/README.md](java-selenium/README.md)

## Resultats

**Robot Framework**: 8 tests (100% de reussite)
- Ajout de taches: 3 tests
- Modification et suppression: 2 tests
- Filtrage: 3 tests

**Java Selenium**: 6 tests (100% de reussite)
- Memes fonctionnalites testees avec une approche differente

## Rapports

### Robot Framework
Rapports generes automatiquement:
- report.html: vue d'ensemble
- log.html: details des etapes
- output.xml: donnees brutes

### Java Selenium
Rapports TestNG dans target/surefire-reports/

## Integration continue

Le projet utilise deux jobs Jenkins distincts avec webhook GitHub pour lancer les tests automatiquement a chaque push.

Pipeline:
1. Push sur GitHub
2. Webhook notifie Jenkins
3. Jenkins clone le repo
4. Les deux jobs se lancent en parallele
5. Rapports publies dans Jenkins

Configuration complete: voir docs/jenkins_setup.md

## Documentation

- plan_de_tests.md: 8 cas de test detailles
- strategie_recette.md: approche globale et process
- jenkins_setup.md: guide installation Jenkins avec les deux jobs
- java-selenium/README.md: infos specifiques au projet Java

## Comparaison des deux approches

### Robot Framework
Avantages:
- syntaxe simple et lisible
- rapide a mettre en place
- parfait pour des tests simples
- facile a maintenir

Inconvenients:
- moins de flexibilite pour des cas complexes
- debugging parfois difficile

### Java Selenium
Avantages:
- controle total sur le code
- pattern Page Object plus propre
- bonne integration dans des projets Java existants
- meilleur pour des gros projets

Inconvenients:
- plus verbose
- setup plus long
- courbe d'apprentissage plus raide

Pour plus de details techniques sur le projet Java, voir java-selenium/README.md

## Ce que j'ai appris

### Tests automatises
- ecrire des cas de test clairs
- automatiser avec deux technologies differentes
- organiser les tests par fonctionnalite
- generer et analyser des rapports

### CI/CD
- configurer deux pipelines Jenkins distincts
- webhooks GitHub
- automatisation complete du process
- environnement Docker
