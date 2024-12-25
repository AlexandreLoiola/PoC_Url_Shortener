create table TB_URL(
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    original_url TEXT UNIQUE NOT NULL,
    short_url VARCHAR(50) UNIQUE NOT NULL,
    created_at DATE NOT NULL,
    expires_at DATE NOT NULL,
    click_count INT DEFAULT 0 NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL
);

INSERT INTO TB_URL (id, original_url, short_url, created_at, expires_at, click_count, is_active)
VALUES
    (RANDOM_UUID(), 'http://localhost:8080/original-url-1', 'abc123', CURRENT_DATE, DATEADD('DAY', 30, CURRENT_DATE), 2, true),
    (RANDOM_UUID(), 'http://localhost:8080/original-url-2', 'xyz789', CURRENT_DATE, DATEADD('DAY', 60, CURRENT_DATE), 15, true),
    (RANDOM_UUID(), 'http://localhost:8080/original-url-3', 'mno456', CURRENT_DATE, DATEADD('DAY', 90, CURRENT_DATE), 3, true);