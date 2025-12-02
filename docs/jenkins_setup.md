# Configuration Jenkins

Documentation technique pour reproduire l'environnement Jenkins avec pipeline CI/CD.

## Prerequis

- Docker installe
- Compte ngrok (gratuit sur https://ngrok.com)
- Acces au repository GitHub

## Installation

### 1. Construction de l'image Docker personnalisee

Le projet utilise une image Jenkins personnalisee qui inclut Python et Firefox.

Fichier `Dockerfile` a la racine du projet:
```dockerfile
FROM jenkins/jenkins:lts

USER root

# Installer Python, pip et Firefox
RUN apt-get update && \
    apt-get install -y python3 python3-pip python3-venv firefox-esr && \
    rm -rf /var/lib/apt/lists/*

# Installer les dependances Python
RUN pip3 install --break-system-packages robotframework robotframework-seleniumlibrary webdriver-manager

USER jenkins
```

Construction de l'image:
```bash
docker build -t jenkins-python .
```

### 2. Lancement du conteneur Jenkins
```bash
docker run -d \
  --name jenkins \
  -p 8080:8080 \
  -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  jenkins-python
```

Attendre 1 minute que Jenkins demarre.

Recuperation du mot de passe initial:
```bash
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Acces a Jenkins: http://localhost:8080

### 3. Configuration initiale de Jenkins

1. Coller le mot de passe initial
2. Choisir "Install suggested plugins"
3. Creer un compte admin (username: mohammed, password: au choix)
4. Garder l'URL par defaut: http://localhost:8080/
5. Cliquer sur "Start using Jenkins"

### 4. Installation des plugins necessaires

Dans Jenkins:
1. Manage Jenkins > Plugins
2. Onglet "Available plugins"
3. Chercher et installer:
   - Git plugin
   - GitHub plugin
   - GitHub Integration Plugin
   - Robot Framework plugin
4. Redemarrer Jenkins si demande

### 5. Creation du job de test

**Type**: Freestyle project
**Nom**: Tests-TodoMVC-Robot-Framework

**Configuration du job**:

**Section General**:
- Description: Tests automatises de l'application TodoMVC avec Robot Framework

**Section Source Code Management**:
- Cocher: Git
- Repository URL: https://github.com/czechmohammed/projet-tests-automatises.git
- Branch Specifier: */main
- Credentials: (laisser vide, repo public)

**Section Build Triggers**:
- Cocher: GitHub hook trigger for GITScm polling

**Section Build Steps**:
- Add build step > Execute shell
- Commande:
```bash
export MOZ_HEADLESS=1
robot --outputdir rapports tests/
```

**Section Post-build Actions**:
- Add post-build action > Publish Robot Framework test results
- Directory of Robot output: rapports

Cliquer sur "Save"

### 6. Exposition de Jenkins via ngrok

Installation de ngrok:
```bash
cd ~
wget https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-amd64.tgz
tar -xvzf ngrok-v3-stable-linux-amd64.tgz
sudo mv ngrok /usr/local/bin/
```

Authentification (recuperer le token sur https://dashboard.ngrok.com):
```bash
ngrok config add-authtoken VOTRE_TOKEN_ICI
```

Lancement de ngrok:
```bash
ngrok http 8080
```

Note: garder ce terminal ouvert, ngrok doit rester actif.

Recuperer l'URL publique affichee (exemple: https://xxx.ngrok-free.dev)

### 7. Configuration du webhook GitHub

Sur GitHub:
1. Aller sur le repository: https://github.com/czechmohammed/projet-tests-automatises
2. Settings > Webhooks > Add webhook
3. Remplir:
   - Payload URL: https://votre-url.ngrok-free.dev/github-webhook/
   - Content type: application/json
   - Secret: (laisser vide)
   - Events: Just the push event
   - Active: cocher
4. Add webhook

Verifier que le webhook est actif avec une coche verte.

## Verification du pipeline

### Test du pipeline complet

Dans un terminal:
```bash
cd ~/projet-tests-automatises
echo "# Test CI/CD" >> README.md
git add README.md
git commit -m "test pipeline automatique"
git push
```

Resultats attendus:
1. GitHub envoie une notification au webhook
2. Jenkins recoit la notification (visible dans les logs ngrok)
3. Un build se lance automatiquement dans Jenkins
4. Les tests s'executent
5. Les rapports sont publies

Dans Jenkins, verifier:
- Un nouveau build apparait avec "Started by GitHub push by czechmohammed"
- Le build passe au vert (SUCCESS)
- Les rapports Robot Framework sont disponibles

## Architecture du pipeline
```
Developpeur
    |
    | git push
    v
GitHub Repository
    |
    | webhook notification
    v
ngrok (tunnel)
    |
    v
Jenkins (Docker)
    |
    | clone repo
    | execute tests
    | publish reports
    v
Rapports disponibles dans Jenkins
```

## Troubleshooting

### Probleme: Jenkins ne demarre pas

Consulter les logs:
```bash
docker logs jenkins
```

Verifier que le port 8080 n'est pas deja utilise:
```bash
sudo netstat -tulpn | grep 8080
```

### Probleme: Les tests echouent avec erreur Firefox

Verifier que Firefox est installe dans le conteneur:
```bash
docker exec jenkins firefox --version
```

Si absent, reconstruire l'image Docker.

### Probleme: Le webhook ne fonctionne pas

Verifier dans GitHub > Settings > Webhooks > Recent Deliveries:
- Response code devrait etre 200
- Si erreur, verifier l'URL ngrok et le slash final

Verifier dans ngrok:
- Ligne "POST /github-webhook/" devrait apparaitre lors d'un push

### Probleme: ngrok se deconnecte

ngrok gratuit peut se deconnecter apres quelques heures.
Relancer simplement:
```bash
ngrok http 8080
```

Mettre a jour le webhook GitHub avec la nouvelle URL.

## Commandes utiles

### Gerer Jenkins
```bash
# Demarrer Jenkins
docker start jenkins

# Arreter Jenkins
docker stop jenkins

# Redemarrer Jenkins
docker restart jenkins

# Voir les logs
docker logs jenkins

# Entrer dans le conteneur
docker exec -it jenkins bash
```

### Gerer ngrok
```bash
# Lancer ngrok
ngrok http 8080

# Voir les requetes ngrok en temps reel
# Ouvrir http://localhost:4040 dans le navigateur
```

### Tester le pipeline
```bash
# Commit vide pour tester
git commit --allow-empty -m "test pipeline"
git push
```

## Notes importantes

- ngrok gratuit change l'URL a chaque redemarrage
- Penser a mettre a jour le webhook GitHub si l'URL change
- Les rapports Jenkins sont persistes grace au volume Docker jenkins_home
- Pour reinitialiser Jenkins completement: docker volume rm jenkins_home
