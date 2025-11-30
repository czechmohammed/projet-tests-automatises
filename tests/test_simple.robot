*** Settings ***
Library    SeleniumLibrary

*** Test Cases ***
Test d'ouverture de Google
    Open Browser    https://www.google.com    chrome
    Title Should Be    Google
    Close Browser
