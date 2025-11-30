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
- Navigateur: Firefox 145
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
- Les temps d'execution restent raisonnables (< 5 min)

### Gestion des echecs
En cas d'echec:
1. Consulter le rapport HTML pour identifier le test en erreur
2. Ouvrir le log detaille pour voir l'etape exacte
3. Reproduire manuellement si necessaire
4. Corriger le bug ou mettre a jour le test
5. Relancer la campagne complete

## Integration continue

### Objectif
Integrer les tests dans un pipeline Jenkins pour:
- Execution automatique a chaque commit
- Notification en cas d'echec
- Archivage des rapports

### Configuration Jenkins (en cours)
- Declenchement: webhook GitHub sur push
- Etapes: installation dependances, execution tests, publication rapports
- Notifications: email en cas d'echec

## Metriques et suivi

### Couverture fonctionnelle
- 8 cas de test pour 7 fonctionnalites principales
- Taux de couverture estime: 85%

### Historique d'execution
Date | Tests executes | Passes | Echoues | Duree
-----|----------------|--------|---------|-------
30/11/2025 | 8 | 8 | 0 | 2m 15s

## Ameliorations futures

### Court terme
- Ajouter des tests pour la persistence localStorage
- Implementer des tests de compatibilite multi-navigateurs
- Ameliorer les temps d'attente (reduire les Sleep)

### Moyen terme
- Integrer les tests dans Jenkins
- Ajouter des tests de performance
- Mettre en place un dashboard de suivi

### Long terme
- Tests de charge avec plusieurs utilisateurs simultanement
- Tests d'accessibilite
- Tests sur devices mobiles

