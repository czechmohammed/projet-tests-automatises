FROM jenkins/jenkins:lts

USER root

# Installer Python, pip et Firefox
RUN apt-get update && \
    apt-get install -y python3 python3-pip python3-venv firefox-esr && \
    rm -rf /var/lib/apt/lists/*

# Installer les dependances Python
RUN pip3 install --break-system-packages robotframework robotframework-seleniumlibrary webdriver-manager

USER jenkins
