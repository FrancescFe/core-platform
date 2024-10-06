DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES
(
    PRICE_LIST BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID   INT        NOT NULL,
    START_DATE TIMESTAMP  NOT NULL,
    END_DATE   TIMESTAMP  NOT NULL,
    PRODUCT_ID INT        NOT NULL,
    PRIORITY   INT        NOT NULL,
    PRICE      DOUBLE     NOT NULL,
    CURRENCY   VARCHAR(3) NOT NULL
);
