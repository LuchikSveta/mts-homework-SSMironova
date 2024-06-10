INSERT INTO animals.animal_types (type) values ('Snake');
INSERT INTO animals.animals (name, breed, character, cost, birth_date, type_id) values ('Lulu', 'Shake1', 'Character1', 3000, '2023-09-10', 6);
UPDATE animals.animals SET secret_information = encode('SECRET_INFORMATION30', 'base64') WHERE id = 11;