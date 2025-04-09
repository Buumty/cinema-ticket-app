-- Tworzenie tabeli filmów
CREATE TABLE IF NOT EXISTS movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE
);

-- Tworzenie tabeli seansów
CREATE TABLE IF NOT EXISTS screenings (
    id SERIAL PRIMARY KEY,
    movie_id INT REFERENCES movies(id) ON DELETE CASCADE,
    start_time TIMESTAMP,
    end_time TIMESTAMP
);

-- Tworzenie tabeli rezerwacji
CREATE TABLE IF NOT EXISTS reservations (
    id SERIAL PRIMARY KEY,
    screening_id INT REFERENCES screenings(id) ON DELETE CASCADE,
    seat_number INT,
    user_name VARCHAR(255)
);

-- Przykładowe dane
INSERT INTO movies (title, description, release_date) VALUES
('Movie 1', 'Opis filmu 1', '2025-05-01'),
('Movie 2', 'Opis filmu 2', '2025-06-01');

-- Dodanie przykładowych seansów
INSERT INTO screenings (movie_id, start_time, end_time) VALUES
(1, '2025-05-01 14:00', '2025-05-01 16:00'),
(2, '2025-06-01 18:00', '2025-06-01 20:00');
