# Strategie de recette - Application TodoMVC

## Contexte

Ce document decrit la strategie de tests pour l'application TodoMVC.
L'objectif est d'assurer la qualite de l'application avant chaque mise en production.

## Perimetre de la recette

### Fonctionnalites testees
- Gestion des taches (ajout, modification, suppression)
- Systeme de filtrage (All, Active, Completed)
- Compteur de taches actives
- Interface utilisateur et interactions

### Fonctionnalites non testees
- Persistence des donnees (localStorage)
- Compatibilite avec tous les navigateurs
- Tests de charge et performance
- Accessibilite

## Types de tests

### Tests fonctionnels automatises
- 8 cas de test couvrant les fonctionnalites principales
- Execution automatique via Robot Framework
- Generation de rapports HTML

### Tests de regression
- Tous les tests sont rejoues avant chaque livraison
- Permettent de verifier qu'aucune regression n'a ete introduite

## Organisation des tests

Les tests sont organises par fonctionnalite:

**test_ajout_tache.robot**
- TC001: Ajouter une tache simple
- TC002: Ajouter plusieurs taches
- TC007: Validation du champ vide

**test_modification_tache.robot**
- TC003: Marquer une tache comme terminee
- TC004: Supprimer une tache

**test_filtrage_taches.robot**
- TC005: Filtrer les taches actives
- TC006: Filtrer les taches completees
- TC008: Retour au filtre All

## Environnement de test

### Configuration technique
- OS: Linux Fedora 42
- Navigateur: Firefox ESR 140
- Framework: Robot Framework 7.3.2
- SeleniumLibrary 6.8.0

### URL testee
https://todomvc.com/examples/react/dist/

## Processus d'execution

### Lancement manuel
```bash
robot tests/
```

### Lancement par fichier specifique
```bash
robot tests/test_ajout_tache.robot
```

### Lancement par tag
```bash
robot --include ajout tests/
robot --include priorite_haute tests/
```

## Analyse des resultats

### Rapports generes
- **report.html**: rapport visuel avec statistiques
- **log.html**: log detaille de chaque etape
- **output.xml**: donnees brutes au format XML

### Criteres de succes
Une campagne de tests est reussie si:
- Tous les tests passent (100% de succes)
- Aucune erreur JavaScript dans la console
- Les temps d'execution restent raisonnables (moins de 5 minutes)

### Gestion des echecs
En cas d'echec:
1. Consulter le rapport HTML pour identifier le test en erreur
2. Ouvrir le log detaille pour voir l'etape exacte
3. Reproduire manuellement si necessaire
4. Corriger le bug ou mettre a jour le test
5. Relancer la campagne complete

## Integration continue avec Jenkins

### Configuration actuelle

Le projet utilise Jenkins pour automatiser l'execution des tests via un pipeline CI/CD complet.

#### Infrastructure

- Jenkins tourne dans un conteneur Docker personnalise
- Image Docker incluant Python 3.13, Robot Framework et Firefox ESR
- Exposition via ngrok pour permettre les webhooks GitHub

#### Pipeline automatise

**Declenchement**: webhook GitHub sur chaque push
- GitHub envoie une notification a Jenkins des qu'il y a un commit
- Jenkins clone automatiquement la derniere version du code
- Les tests se lancent sans intervention manuelle

**Execution des tests**:
```bash
export MOZ_HEADLESS=1
robot --outputdir rapports tests/
```
- Firefox s'execute en mode headless (sans interface graphique)
- Les 8 tests tournent automatiquement
- Duree moyenne: 1 minute

**Publication des resultats**:
- Rapports HTML generes automatiquement
- Interface Robot Framework integree dans Jenkins
- Graphiques et statistiques d'evolution des tests

#### Historique d'execution

Date | Build | Tests executes | Passes | Echoues | Duree | Declencheur
-----|-------|----------------|--------|---------|-------|-------------
01/12/2025 | 3 | 8 | 8 | 0 | 54s | GitHub push
01/12/2025 | 2 | 8 | 8 | 0 | 54s | GitHub push 
30/11/2025 | 1 | 8 | 8 | 0 | 45s | Manuel

#### Avantages observes

- **Gain de temps**: tests lances automatiquement, pas besoin de les lancer manuellement
- **Detection rapide**: les bugs sont detectes des le push
- **Tracabilite**: historique complet de tous les builds
- **Rapports automatiques**: plus besoin de generer les rapports a la main

#### Ameliorations possibles

**Court terme**:
- Notifications par email en cas d'echec
- Badge de statut des tests dans le README GitHub
- Archivage automatique des rapports

**Moyen terme**:
- Tests de performance integres au pipeline
- Deploiement automatique en environnement de recette si tous les tests passent
- Tests paralleles pour accelerer l'execution

**Long terme**:
- Pipeline multi-environnements (dev, recette, production)
- Integration avec Jira pour la gestion des bugs
- Dashboard Grafana pour suivre les metriques

## Metriques et suivi

### Couverture fonctionnelle
- 8 cas de test pour 7 fonctionnalites principales
- Taux de couverture estime: 85%

### Temps d'execution
- Temps moyen par test: 7 secondes
- Temps total de la suite: 54 secondes
- Temps acceptable pour une integration continue

## Ameliorations futures

### Court terme
- Ajouter des tests pour la persistence localStorage
- Implementer des tests de compatibilite multi-navigateurs
- Ameliorer les temps d'attente (reduire les Sleep)

### Moyen terme
- Ajouter des tests de performance
- Mettre en place un dashboard de suivi
- Tests sur devices mobiles

### Long terme
- Tests de charge avec plusieurs utilisateurs simultanement
- Tests d'accessibilite
- Integration avec outils de monitoring
