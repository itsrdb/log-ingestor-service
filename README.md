<!-- ABOUT THE PROJECT -->
## Log-Ingestor and Query Interface

This project allows users or services to send logs in to be stored in a database and provides an interface to search/filter logs with queries.

What does it do?:
* The API server accepts a list of logs in Json format and stores them in a Postgres database.
* The database used is of relational type and thus supports a wide range of query types.
* The results are displayed in a table separating the logs into rows and logs are further shown in column style for better readability.

### What is implemented?
1. Log ingestor listens on the port 3000 and accepts a list of logs in this format:
```
{
    "logMessageItemsDtoList":[
        {
            "level": "warn-2",
            "message": "warning",
            "resourceId": "server-1234",
            "timestamp": "2023-11-16T08:00:00Z",
            "traceId": "ops-xyz-123",
            "spanId": "span-456",
            "commit": "sx5342f",
            "metadata": {
                "parentResourceId": "server-1230"
            }
        }
    ]
}
```
2. Web-based UI Query Interface allows users to easily search fields (if the user doesn't want to specify a field, it can be left blank and no query will be applied on the particular field).
<br>
<img src="https://github.com/dyte-submissions/november-2023-hiring-itsrdb/blob/b4bb98c78dceffe71106dd56ac97e76308cde0da/images/snap-1.png" height="500"></img>
<img src="https://github.com/dyte-submissions/november-2023-hiring-itsrdb/blob/b4bb98c78dceffe71106dd56ac97e76308cde0da/images/snap-2.png" height="500"></img>
3. Single attribute queries, multiple attribute queries and substring search are allowed for better results.
   For example, searching for `debug` Level and a substring `db` in Message results in this:
<br><br>
<img src="https://github.com/dyte-submissions/november-2023-hiring-itsrdb/blob/93a28faa4f595c28f0d397fd5986714e2e594078/images/snap-3.png" width="900"></img>
4. Time range filters are also supported, user can either choose beforeDate or afterDate or both for getting filtered results.<br><br>
5. Indexing is done on model attributs `level` and `resource-id` using multi-column indexing to avoid slowing down in writing tasks while also providing faster results. Chose these attributes because of their constant nature most of the time.


### Built With

* Java Spring Boot
* Postgres SQL
* QueryDSL
* Javascript

<!-- GETTING STARTED -->
## Getting Started

Install IntelliJ and MySQL Databases for getting started.

### Prerequisites

1. Install Java17
2. Install MySQL
4. Install LiveServer

### Installation

1. Clone the repo
   ```sh
   git clone this repo: https://github.com/dyte-submissions/november-2023-hiring-itsrdb.git
   ```
2. Sync Maven Dependencies
3. Create Database Schema named 'logging_service' using MySQL, table creaton and management will be taken care by Spring Boot
4. Use LiveServer to open Index.html

### What can be further improved?
1. Introducing Microservices to GET and POST services for better load handling.
2. Sharding the database based on timestamp and script to move old data to other databases (past 30-days logs could be stored in a separate database and rest of the collection on some other databases)
3. Introducing Load balancing on services to maintain consistency on writing and reading tasks.

<!-- CONTACT -->
## Contact

Your Name - [@itsrdb](https://www.linkedin.com/in/itsrdb/) - rohitborkar00@gmail.com

Project Link: [https://github.com/dyte-submissions/november-2023-hiring-itsrdb.git](https://github.com/dyte-submissions/november-2023-hiring-itsrdb.git)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
