# Chat4You-Android

## How to operate the server


## Prerequisites

You need to install first:

- Android-Studio - https://developer.android.com/studio
- .NET - https://dotnet.microsoft.com/en-us/download
- MariaDB - https://mariadb.org/download/


## Get the code
Use those commands:

```
git clone https://github.com/roeige/Chat4You-Android.git
cd Chat4You-Android
```

## Run Server

Please open the appsettings.json files in server/webAPI, and change the DB section username and password to those you declared in your mariaDB installation.

It is recommended for you to delete the Repository/Migrations folder, and type those commands on the NuGet CLI:

```
add-migration init
database-update
```

you can start the server by this command: (make sure you are in the main directory - ./Chat4You-Android
```
dotnet run --project server/webAPI
```

## Run Android Client

After the server is up, you can run the Android studio code and start to chat.

Happy chatting!
