# Chat4You-Android

## How to operate the server


## Prerequisites

You need to install first:

- Node.js and npm - https://nodejs.org/en/
- .NET - https://dotnet.microsoft.com/en-us/download
- MariaDB - https://mariadb.org/download/


## Get the code

אוריאל תשלים !!!!!!!

## Run

Please open the appsettings.json files in server-api/webAPI, and change the DB section username and password to those you declared in your mariaDB installation.

It is recommended for you to delete the Repository/Migrations folder, and type those commands on the NuGet CLI:

```
add-migration init
database-update
```

run these commands:

```
cd client-chat
npm install
cd ..
npm install
```

it will install the dependencies in node_moudles

## opreate the Android application

After the server is up, you can run the Android studio code and start to chat.

Happy chatting!
