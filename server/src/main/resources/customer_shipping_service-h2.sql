;
CREATE USER IF NOT EXISTS "SA" SALT 'f7acd497c04aed3a' HASH '4cbec2372dd59655ecd036f6c935d056bb3155dd91d60085f34a544dc8a89e7d' ADMIN;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_21559626_1D5A_41DB_8B0F_F00885048647" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D2FB023E_5FC6_44BA_8CE3_F329B7683803" START WITH 4 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."CUSTOMER_SHIPPING_SERVICE"(
    "ID" VARCHAR(255)  DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D2FB023E_5FC6_44BA_8CE3_F329B7683805") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D2FB023E_5FC6_44BA_8CE3_F329B7683805",
    "CUSTOMER_ID" VARCHAR(255) NOT NULL,
    "SERVICE" VARCHAR(255) NOT NULL,
    "OPTION" VARCHAR(255) NOT NULL
);
ALTER TABLE "PUBLIC"."CUSTOMER_SHIPPING_SERVICE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");
ALTER TABLE "PUBLIC"."CUSTOMER_SHIPPING_SERVICE" ADD CONSTRAINT "PUBLIC"."FKE5W4HCLWYTMJ7JLGI069POEW1" FOREIGN KEY("CUSTOMER_ID") REFERENCES "PUBLIC"."CUSTOMER"("ID") NOCHECK;
