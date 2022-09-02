# Konzept
## Sinn der App
Die App ist ein sehr simpler Wecker.
## Zielgruppe
Personen die am Morgen nicht aus dem Bett kommen. 
## Anforderungen
Die Anforderungen der App können in die funktionalen Anforderungen und nicht funktionalen Anforderungen unterteilt werden. Funktional bedeuted es sind wichtige funktionen in der App, nicht funktional sind 
### Funktional:

#### minimum:
- Wecker
  - Zeit kann eingestellt werden
  - Wecker kann deaktiviert werden
  - Klingelton kann ausgewählt werden.

#### extras:
- NFC integration
- Traumtagebuch
- opt.
  - Handy schütteln
  - Glyph interface (leider nicht möglich)

### Nicht funktional:

- Die App sollte einfach zu bedienen sein
- Die App sollte einfach zu verstehen sein
- Alle Schaltflächen und Texte sollten gross genug sein
- Passende Farben

## Mockups
Das Mockup der App kann unter dem folgenden link eingesehen werden:
https://app.moqups.com/n7od7V1loXsC5z7gWRkzn8V1ZoeFjA1U/view/page/ad64222d5

## Architektur
Die App besitzt sehr wenig Logik, eine eigene Klasse hätte sich nicht gelohnt. Deshalb wird diese auch in den Ui-Klassen geschrieben.

## Verwendete Technologien
Die gesamte App wurde mit Kotlin geschrieben.
Die Entwicklung wurde mit Android Studio umgesetzt.
Ansonsten wurden keine weiteren Technologien verwendet.

## Diagramme

## Testkonzept

| Identifikation | Vorbedingungen | Vorgehen | Erwartetes Resultat |
|-|-|-|-|
| FishAlarm-Test-01 | 1. App ist geöffnte <br> 2. Wecker ist Aktiviert | Zeit auswählen mit Time Picker | Wecker geht um die angegebene Zeit los |
| FishAlarm-Test-02 | 1. App ist geöffnte | Wecker mit Switch aktivieren | Wecker geht um die angegebene Zeit los |
