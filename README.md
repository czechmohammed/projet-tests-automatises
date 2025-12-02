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
- Firefox ESR 140
- Jenkins (Docker)
- ngrok (exposition publique)

### Projet 2: Java Selenium (a venir)
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
├── rapports/                       # Rapports de tests generes (git ignore)
├── docs/
│   ├── plan_de_tests.md           # Plan de tests detaille
│   ├── strategie_recette.md       # Strategie globale
│   └── jenkins_setup.md           # Guide installation Jenkins
├── Dockerfile                      # Image Jenkins personnalisee
├── requirements.txt                # Dependances Python
└── README.md
```

## Installation

### Prerequis
- Python 3.x
- pip
- Firefox
- Docker (pour Jenkins)

### Installation des dependances
```bash
pip3 install -r requirements.txt
```

Ou manuellement:
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

## Integration continue

### CI/CD avec Jenkins et webhook GitHub

Le projet utilise un pipeline CI/CD complet:

1. Push du code sur GitHub
2. GitHub envoie une notification via webhook
3. Jenkins clone automatiquement le repo
4. Les tests se lancent en mode headless
5. Rapports publies automatiquement dans Jenkins

Configuration detaillee: voir [docs/jenkins_setup.md](docs/jenkins_setup.md)

### Historique des builds

Les derniers builds sont visibles dans Jenkins avec:
- Statut (succes/echec)
- Duree d'execution
- Declencheur (push GitHub ou manuel)
- Rapports Robot Framework integres

## Documentation

- [Plan de tests](docs/plan_de_tests.md): description detaillee des 8 cas de test
- [Strategie de recette](docs/strategie_recette.md): approche globale et processus
- [Guide Jenkins](docs/jenkins_setup.md): installation et configuration complete

## Ce que j'ai appris

### Tests automatises
- Ecriture de cas de test clairs et reproductibles
- Automatisation avec Robot Framework
- Organisation des tests par fonctionnalite
- Generation et analyse de rapports

### CI/CD
- Configuration d'un pipeline Jenkins
- Integration avec GitHub via webhooks
- Automatisation complete du processus de test
- Docker pour l'environnement reproductible

### Bonnes pratiques
- Documentation technique complete
- Versionning avec Git
- Structure de projet claire
- Tests maintenables et reutilisables

## Prochaines etapes

- [ ] Projet parallele avec Java + Selenium
- [ ] Tests de performance
- [ ] Tests multi-navigateurs
- [ ] Notifications par email en cas d'echec

