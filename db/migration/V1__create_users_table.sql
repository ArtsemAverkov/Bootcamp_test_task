 CREATE TABLE userClient
    (
    last_name VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    first_name VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    patronymic VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    email VARCHAR(50) NOT NULL,
    role ENUM('Administrator', 'Sale User', 'Customer User', 'Secure API User') NOT NULL
    );