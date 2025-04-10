-- Assuming you are already connected to the "cinema" database
CREATE database cinema;
-- Create table for movies
DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
    name text,
    length integer,
    description text
);

-- Note: The tables reserved_seat and seance are managed dynamically and do not need manual initialization.

-- Create table for rooms.
-- The "seats_number" column is generated automatically as rows * colums.
DROP TABLE IF EXISTS room;
CREATE TABLE room (
    rows integer,
    name text,
    columns integer,
    seats_number integer GENERATED ALWAYS AS (rows * columns) STORED
);

-- Create table for tickets
DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
    type text,
    price double precision
);

-- Insert sample data into movie table
INSERT INTO movie (name, length, description) VALUES
  ('Inception', 148, 'A mind-bending thriller about dreams and reality.'),
  ('The Matrix', 136, 'A revolutionary sci-fi film exploring a simulated reality.');

-- Insert sample data into room table
INSERT INTO room (rows, name, columns) VALUES
  (10, 'Room A', 20),
  (8, 'Room B', 15);

-- Insert sample data into ticket table
INSERT INTO ticket (type, price) VALUES
  ('adult', 10.50),
  ('child', 7.50),
  ('senior', 8.00);