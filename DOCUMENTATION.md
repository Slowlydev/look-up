# look-up - Dokumentation

## Inhalt

1. [Beschreibung des Projekts](#beschreibung)
2. [Konkurrenzanalyse](#konkurrenzanalyse)
3. [User Stories](#user-stories)
4. [Mockups](#mockups)
5. [Technische Realisierung](#technische-realisierung)
6. [Testing](#testing)
7. [Fazit](#fazit)

## Beschreibung

Die Android-Wetter-App, entwickelt unter Verwendung der [Weather-API](https://www.weatherapi.com/api-explorer.aspx), bietet eine benutzerfreundliche Anwendung, die aktuelle und präzise Wetterinformationen in Echtzeit liefert. Mit dieser App können Sie mühelos das Wetter für jeden Ort auf der Welt abrufen.

Die App verfügt über eine intuitive Benutzeroberfläche, die klare Informationen zur Temperatur, Luftfeuchtigkeit, Windgeschwindigkeit, Niederschlagswahrscheinlichkeit und mehr bietet. Sie können die Wetterdaten automatisch für Ihren aktuellen Standort abrufen oder nach beliebigen Orten weltweit suchen und speichern.

## Konkurrenzanalyse

In der Konkurrenzanalyse betrachten wir verschiedene Android-Wetteranwendungen mit einer gewissen Bekanntheit. Wir analysieren ihre Vorteile, die möglicherweise in unsere App integriert werden können, und vermeidbare Nachteile.

- **Weather Underground**:

  - Positiv: Diese App bietet präzise Wetterdaten und eine benutzerfreundliche Oberfläche. Sie verfügt über eine engagierte Community von Wetter-Enthusiasten und liefert detaillierte Informationen.
  - Negativ: Gelegentlich kann die App störende Anzeigen enthalten, und einige Benutzer haben Probleme mit der Aktualisierung der Wetterdaten gemeldet.

- **WeatherBug**:

  - Positiv: WeatherBug bietet Echtzeit-Wetterdaten und Warnmeldungen. Die App enthält auch Wetterkarten und Radarbilder.
  - Negativ: WeatherBug enthält störende Anzeigen, die die Benutzererfahrung beeinträchtigen können. Auf älteren Geräten kann die App langsam sein, und um Anzeigen zu entfernen, muss eine Premium-Version erworben werden.

- **The Weather Channel**:

  - Positiv: Die offizielle App von The Weather Channel bietet stündliche und 10-Tage-Wettervorhersagen. Sie enthält auch Funktionen wie Wetterkarten und Videoberichte.
  - Negativ: Die App enthält Anzeigen und Pop-ups, die die Benutzererfahrung beeinträchtigen können. Einige Benutzer empfinden die Oberfläche als überladen und schwer zu navigieren.

- **AccuWeather**:

  - Positiv: AccuWeather bietet minutengenaue Wettervorhersagen und Wettermeldungen. Die App enthält auch Informationen zur Luftqualität und Allergien.
  - Negativ: Die kostenlose Version von AccuWeather enthält störende Anzeigen, was einige Benutzer als lästig empfinden. Die App kann aufgrund ihrer Fülle an Informationen und Funktionen für einige Benutzer zu komplex sein.

- **1Weather**:
  - Positiv: 1Weather zeichnet sich durch sein ansprechendes Design und seine benutzerfreundliche Schnittstelle aus. Sie bietet genaue Wetterdaten und Widgets.
  - Negativ: Obwohl die App eine kostenlose Version mit Anzeigen anbietet, kann dies die Benutzererfahrung beeinträchtigen. Einige Nutzer haben auch berichtet, dass die App auf älteren Android-Geräten nicht so reibungslos läuft.

Zusammenfassend können wir feststellen, dass die Konkurrenz-Apps einen starken Fokus auf Features und Visualisierung legen. Angesichts der Herausforderungen, eine App in einer für unser Team neuen Sprache in nur drei Tagen zu entwickeln, wird es schwierig sein, umfangreiche Datenvisualisierungen oder zusätzliche Features umzusetzen.

Unser Hauptziel ist es jedoch, eine benutzerfreundliche App bereitzustellen, die alle benötigten Wetterdaten einfach zugänglich macht.

## User Stories

### Für die MainActivity:

1. **Als Benutzer** möchte ich **eine Liste von Orten sehen**, um **schnell den Ort auszuwählen, für den ich das Wetter erfahren möchte**.

2. **Als Benutzer** möchte ich **auf einen Ort klicken können**, um **weitere Wetterinformationen für diesen Ort anzuzeigen**.

3. **Als Benutzer** möchte ich **aktuelle Wetterinformationen für den ausgewählten Ort sehen**, um **einen schnellen Überblick über das Wetter zu erhalten**.

### Für die WeatherActivity:

4. **Als Benutzer** möchte ich **eine übersichtliche und einfache Benutzeroberfläche (UI)** haben, um **grundlegende Wetterdaten für den ausgewählten Ort leicht verständlich zu sehen**.

5. **Als Benutzer** möchte ich **einen Button haben, der mich zur DetailedWeatherActivity führt**, um **weitere detaillierte Wetterinformationen zu erhalten, wenn ich sie benötige**.

### Für die DetailedWeatherActivity:

6. **Als Benutzer** möchte ich **genaue und detaillierte Informationen über den aktuellen Zustand des Wetters sehen**, um **mich auf ändernde Wetterbedingungen vorzubereiten**.

7. **Als Benutzer** möchte ich **stündliche Temperaturdaten für den ausgewählten Ort erhalten**, um **meine Aktivitäten entsprechend zu planen**.

8. **Als Benutzer** möchte ich **grafische Darstellungen von Wetterdaten sehen**, um **Trends und Muster leichter zu erkennen**.

## Mockups

Unsere Mockups wurden alle in Adobe XD erstellt und als Gruppe entworfen.

1. **Home Screen**
   ![Location-Screen](/assets/locations-screen.png)

   - Der Locations(Home) Screen bietet eine einfache Übersicht über die zuletzt gesuchten Orte. Er bietet auch die Möglichkeit, durch einen Klick auf einen Ort weitere Informationen anzuzeigen.

2. **Weather Screen**
   ![Home-Screen](/assets/home-screen.png)

   - Der Weather Screen wurde bewusst einfach und übersichtlich gestaltet. Der Ort befindet sich oben in der Mitte mit ausreichendem Abstand zu den Seiten. Die Temperaturanzeige und der Wetterzustand sind prominent platziert. Zusätzliche Informationen sind in der weißen Box unten verfügbar, zusammen mit einem gut erkennbaren Button, der zu einer anderen Seite führt. Der Hintergrund passt sich je nach Tageszeit an (Nacht, Tag, Morgen, Abend).

3. **More Details Screen**
   ![More-Details-Screen](/assets/more-details-screen.png)

   - Der More Details Screen wird durch einen Klick auf den Wheater Screen erreicht. Hier werden automatisch die Daten des ausgewählten Ortes geladen. Diese Seite bietet Informationen, die möglicherweise nicht auf den ersten Blick benötigt werden. Das Design hält sich an klare Abstände und Strukturen.

## Technische Realisierung

Folgende Tools wurden verwendet:

- Java
- Android Studio
- ChatGPT

Wir begannen die Entwicklung mit Kotlin, in der Hoffnung, die modernere und möglicherweise einfachere Sprache zu nutzen. Leider stießen wir auf Schwierigkeiten, da es weniger Material gab und einige Kotlin-Pakete in Android Studios nicht gut funktionierten. Daher entschieden wir uns als Team, zu Java zu wechseln. Das Einrichten und der initiale Commit wurden von Slowlydev durchgeführt.

Nach der Einrichtung teilten wir die Aufgaben auf. Da wir drei Seiten hatten, wurde jede Seite einer Person zugewiesen. Simylein übernahm die MainActivity, Slowlydev die WeatherLocationActivity und Blize die DetailedWeatherActivity. Jeder hatte sein eigenes Design (Siehe auch [Mockup](#mockups)) und wusste, was zu tun war. Nachdem die Implementierung abgeschlossen war, kümmerte sich Slowlydev um das Routing und Simylein um das Error Handling.

Danach hatten wir folgende Aufgaben (nach Priorität geordnet):

1. Suche auf Slowlydev's Seite
2. Testing
3. Dokumentation vervollständigen
4. Icons laden (Optional)
5. Hintergrund dynamisch laden (Optional)

Slowlydev übernahm Aufgabe 1 und 5, während Aufgabe 2 von Simylein übernommen wurde und Nummer 3 von allen Teammitgliedern durchgeführt wurde.
Nummer 4 wurde von Blize übernommen. Zusätlich haben wir noch ein Horizontel Scrollbare Liste eingebaut um alle Uhrzeiten anzuzeigen.

## Testing

1. **MainActivity**
   Wir testen ob die Homepage die liste rendered, wenn man ihr eine Liste von locations in die preferences schreibt.
   Die Homepage sollte `no saved locations :(` anzeigen wenn die preference list leer ist.
   Die Homepage sollte `no network connection :(` anzeigen wenn der user keine Verbindung hat.
   Die Liste sollte für jede Location die Ortschaft, Temperatur und den Wetter Condition Text anzeigen.
2. **SearchableActivity**
   Wir testen ob man nach einer Location suchen kann.
   Bei der Auswahl sollte man auf eine location klicken können und dabei auf die Deteilseite gelangen.
   Die ausgewählte location sollte danach auf der Homepage erscheinen.
   Bei keinen Resultaten sollte ein text gerendert werden.
   Bei keiner Verbindung sollte einen Fehlermeldung angezeigt werden.
3. **WeatherLocationActivity**
   Die Wetterseite hat einen Passenden Background.
   Die Wetterseite zeigt die Location im Header an.
   Die Wetterseite zeigt die aktuelle Temperatur gross an.
   Die Wetterseite zeigt eine kleine Vorschau auf die nächsten 4 Stunden an
4. **DetailedWeatherActivity**
   Diese Deteilseite zeigt die Location im Header an.
   Sie zeigt ausserdem Local Time, Temperatre, Feels like, Wind direction und Wind Speed an.
   Darunter wird eine grössere Vorschau der nächsten 8 Studen angezeigt.

## Fazit

Das Fazit für diese Android-Wetter-App fällt gemischt aus. Obwohl unser Entwicklungsteam zuvor keine Erfahrung mit Kotlin oder Java hatte und auf viele Herausforderungen stieß, die wir nur langsam überwinden konnten, können wir dennoch auf eine zufriedenstellende Leistung zurückblicken.

Dank der Motivation und Fleiss in unserem Team, konnten wir erfolgreich alle Features implementieren und fast alles in unserem UI vervollständigen.

Die App ermöglicht es den Benutzern, aktuelle und präzise Wetterinformationen abzurufen. Wir haben wertvolle Erfahrungen gesammelt und sind bereit, unser Wissen und unsere Fähigkeiten in zukünftigen Projekten weiter auszubauen.
