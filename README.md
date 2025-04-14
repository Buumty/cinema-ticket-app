# 🎬 Cinema Ticket App

Aplikacja do rezerwacji biletów do kina napisana w Javie z użyciem Spring Boot. Projekt demonstracyjny, który umożliwia przeglądanie seansów filmowych, wybór miejsc na sali i rezerwację biletów.

## 🧠 Opis projektu

Projekt umożliwia:
- Dodawanie i przeglądanie filmów
- Tworzenie sal kinowych i przypisywanie do nich seansów
- Wizualne przedstawienie sali kinowej (siedzeń)
- Rezerwację miejsc na konkretny seans
- Walidację rezerwacji (np. czy miejsce nie zostało już zajęte)

Projekt jest w trakcie rozwoju i służy do nauki tworzenia aplikacji full-stack w Javie.

## 🛠️ Technologie

- **Java 21**
- **Spring Boot**
- **Vaadin** (UI)
- **Maven**
- **PostgreSQL**
- **JUnit**

## 🐳 Wymagania

- **Docker**

## 📁 Struktura katalogów


      ```
      src/
      ├── main/
      │   ├── java/pl/wojtekandrzejczak/cinema/
      │   │   ├── cinema_hall/       <- logika sal kinowych
      │   │   ├── movie/             <- filmy i seanse
      │   │   ├── reservation/       <- rezerwacje miejsc
      │   │   └── CinemaApplication  <- punkt startowy aplikacji
      │   └── resources/
      │       └── application.properties
      └── test/
            └── java/...               <- testy jednostkowe

## 🚀 Jak uruchomić

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/Buumty/cinema-ticket-app.git
   cd cinema-ticket-app
   
2. Uruchom aplikację:
    ````
    docker-compose up --build

3. Przejdź do przeglądarki i otwórz: http://localhost:8080

## Autor

Wojciech Andrzejczak
