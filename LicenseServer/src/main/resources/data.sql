DROP TABLE IF EXISTS channel;
DROP TABLE IF EXISTS license;
 
CREATE TABLE license (
  device_Id INT PRIMARY KEY,
  deviceName VARCHAR(250) NOT NULL
);

CREATE TABLE channel (
  channel_Id INT PRIMARY KEY,
  channelName VARCHAR(250) NOT NULL,
  device_Id INT
);
