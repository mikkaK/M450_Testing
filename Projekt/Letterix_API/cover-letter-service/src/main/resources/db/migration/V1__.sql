CREATE TABLE chat_completion
(
    id                UUID    NOT NULL,
    object            VARCHAR(255),
    created           BIGINT  NOT NULL,
    model             VARCHAR(255),
    content           TEXT,
    cover_letter_id   UUID,
    prompt_tokens     INTEGER NOT NULL,
    completion_tokens INTEGER NOT NULL,
    total_tokens      INTEGER NOT NULL,
    CONSTRAINT pk_chat_completion PRIMARY KEY (id)
);

CREATE TABLE cover_letter
(
    id                      UUID NOT NULL,
    name                    VARCHAR(255),
    surname                 VARCHAR(255),
    age                     VARCHAR(255),
    applied_company         VARCHAR(255),
    position                VARCHAR(255),
    current_education_level VARCHAR(255),
    skills                  TEXT[],
    interests               TEXT[],
    weaknesses              TEXT[],
    CONSTRAINT pk_cover_letter PRIMARY KEY (id)
);

ALTER TABLE chat_completion
    ADD CONSTRAINT FK_CHAT_COMPLETION_ON_COVER_LETTER FOREIGN KEY (cover_letter_id) REFERENCES cover_letter (id);