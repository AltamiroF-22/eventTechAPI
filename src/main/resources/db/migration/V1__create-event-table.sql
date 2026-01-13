CREATE Extension IF NOT EXISTS "pgcrypto";

 CREATE TABLE event (
      id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

      title VARCHAR(255) NOT NULL,

      description VARCHAR(1000) NOT NULL,

      img_url VARCHAR(500) NOT NULL,

      event_url VARCHAR(500) NOT NULL,

      date TIMESTAMP NOT NULL,

     remote Boolean NOT NULL
  );