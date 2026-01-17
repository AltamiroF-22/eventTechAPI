CREATE Extension IF NOT EXISTS "pgcrypto";

 CREATE TABLE coupon (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    
    code VARCHAR(100) NOT NULL,

    discount INT NOT NULL,

    valid TIMESTAMP NULL,

    event_id UUID NOT NULL REFERENCES event(id) ON DELETE CASCADE

  );