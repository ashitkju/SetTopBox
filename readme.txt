Greetings from the Author!!!

As you have extracted the zip file you could open the two projects in your java development IDE
run both the projects as Spring boot application.
and play around with the Resources it offers in Controller java files.

Here SetTopBox service is client microservice which talks with LicenseService to obtain licenses.
LicenseService stores the data in in-memory H2 database(One to Many mapping demonstration).

As Browser is not capable of doing HTTP request beyond GET, so we will be using Postman.

Payload and request:

LicenseService:
Ping and test application is up and working.  
GET : http://localhost:8123/ping

Get All Licenses.
GET : http://localhost:8123/licenses

Get a particular Device licence
GET : http://localhost:8123/licenses/{id}

Create or Update a license by content Owner.
POST : http://localhost:8123/addOrUpdateLicense
    {
        "deviceName": "Ravi",
        "channels": [
            {
                "channelName": "Cartoon"
            },
            {
                "channelName": "Netflix"
            },
            {
                "channelName": "Amazon Prime"
            }
        ]
    }

Delete a licence.
DELETE :  http://localhost:8123/removeDevice/{id}

SetTopBox service:
Ping and test application is up and working.  
GET : http://localhost:8124/ping

Obtain licences for one device by talking with License Micro service
POST: http://localhost:8124/verify/{id}

Watch a channel
POST: http://localhost:8124/watch/{channel}

To see Physical Data in DB, go to below URL and use "sa" as username and "password" as password.
http://localhost:8123/h2-console


for any more queries, contact: ashit.singh@nagra.com