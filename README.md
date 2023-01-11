<h1 align="center">
  <br>
  <div href="https://nordeck.net/" style="text-align: center;"><img src="https://nordeck.net/wp-content/uploads/2020/05/NIC_logo_Nordeck-300x101.png" alt="Markdownify" width="200"></div>
  Jitsi Conference Mapper
  <br>
</h1>
<h4 align="center">A SIP-Call Mapper for <a href="https://jitsi.org/" target="_blank">Jitsi</a>.</h4>

<div align="center">

• [TL;DR](#TL;DR)
• [Startup](#Startup) 
• [Contribute](#Contribute) 
• [Requirements](#Requirements) 
• [Configuration](#Configuration) 
• [Deployment](#Deployment) 
• [License](#License) 
• [Sponsors](#Sponsors) •

</div>

## TL;DR

- Helps to participate in a Jitsi Meet conference by SIP-Call.
- A SIP-Call is not possible via `https://meet.domain.org/<ConferenceName>` so the App maps from `<ConferenceName>` to a 6-10-digit number
- If `<ConferenceID>` is entered via `GET`, `<ConferenceName>` is returned
- If `?conference=<ConferenceName>` then `<ConferenceID>` is returned. 

The Selfhosted Jitsi Conference Mapper helps phone callers join a Jitsi Meet conference by SIP-Call.
People joining by web or the Jitsi Meet Electron app click on a link like `https://meet.domain.org/<MyConferenceName>`.

However, those calling by phone cannot simply type the `<ConferenceName>` into their phone. Clarified: If you enter `id=04023` via `GET`, the name of the conference will be returned. If you enter `conference=KarlWasHere`, the ID is returned (technically, both are returned always).

The very first time a specific conference name is entered an n-digit number with n=6-9 digits number is generated and stored. After that, this pair is persisted - and reused for all requests.

## Startup

Development on the Jitsi conference mapper app happens at [GitHub](https://github.com/nordeck/Jitsi-Conference-Mapper).

## Contribute

Please take a look at our [Contribution Guidelines](https://github.com/nordeck/.github/blob/main/docs/CONTRIBUTING.md).

## Requirements

You need to have Docker, Maven and Java installed. 

## Configuration

Tell Jitsi to use this app as an API for Mapping. Replace `<meet.domain.com> ` with your jitsi domain:

`sudo nano /etc/jitsi/meet/meet.domain.com-config.js`

Modify the dialConfCodeULR line to match your new path

`CONFCODE_URL: 'https://<meet.domain.com>/conferenceMapper/`

Use the provided [application.yml](./src/main/resources/application.yaml) and fill it with your configuration.
For a list of available options, see [Configuration](./.docs/configuration.md).

## Deployment

In order to make the Application work properly you need to setup the database using Docker:

`docker run --rm --name jitsi_mapper__postgres -e POSTGRES_PASSWORD=postgres  -p 5432:5432 postgres`

### Build the Application using Maven

`cd 'path/to/Jitsi Conference Mapper' | mvn clean install package`

Yon can run the app using

`java -jar target/<VERSION_TAG>-SNAPSHOT.jar`

### Optional: Build the application using docker 

1. Build the App using Docker

`docker build -t nordeck/jcm '.'`

2. Setup the database using Docker as mentioned above.

> **Note** If you want to run the App via Docker it's mandatory to open a Docker Network (via Docker Compose e.g.) 
so the app and the database can communicate;

### Optional: Deploy via HELM release

1. Change directory

`cd '<path/to/Jitsi Conference Mapper>/helm-charts/jitsi-conference-mapper'`

2. Add Helm repo and install Postgres DB

`helm repo add bitnami https://charts.bitnami.com/bitnami; helm dependency build`

`helm install --upgrade --namespace <YourFavouriteNameSpace> jitsi-conference-mapper '.'`

## License

This project is licensed under [APACHE 2.0](./LICENSE).

## Sponsors

<p align="center">
   &nbsp;
   <a href="https://www.dataport.de/"><img src="./.docs/logos/dataportlogo.png" alt="Dataport" width="20%"></a>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <a href="https://www.nordeck.net/"><img src="./.docs/logos/nordecklogo.png" alt="Nordeck" width="20%"></a>
</p>
