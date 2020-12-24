## General contexte 

Enterprise Applications are often built in three main parts: a
client-side user interface (consisting of HTML pages and Javascript
running in a browser on the user\'s machine) a database (consisting of
many tables inserted into a common, and usually relational, database
management system), and a server-side application that will handle HTTP
requests, execute domain logic, retrieve and update data from the
database, and select and populate HTML views to be sent to the browser.
This server-side application is a *monolith* - a single logical
executable. Which have a lot of drawbacks:

-   **Scalability**: Monolithic applications are difficult to scale up
    once they get larger.

-   **Reliability**: Difficult to maintain, an error in any of the
    modules in the application can bring the entire application down.

-   **Continuous deployment**: Due to a single large codebase and tight
    coupling, in order to update one component, we have to redeploy the
    entire application.

-   ![](media/image1.png){width="3.0in"
    height="1.6131944444444444in"}**Agility** in terms of technology
    stack: A monolithic application must use the same technology stack
    throughout. Changes to the technology stack are expensive, both in
    terms of the time and cost involved.

With all drawbacks of Monolithic architecture, Microservices which
consist of loosely-coupled, independent services, comes as solution to
the most of these issues.

microservice architectural style is an approach to developing a single
application as a suite of small services, each running in its own
process and communicating with lightweight mechanisms, often an HTTP
resource API. These services are built around business capabilities and
independently deployable by fully automated deployment machinery. There
is a bare minimum of centralized management of these services, which may
be written in different programming languages and use different data
storage technologies.

-   **Flexibility**: Microservices architecture is quite flexible.
    Different microservices can be developed in different technologies.
    Since a microservice is smaller, the code base is quite less, so
    it's not that difficult to upgrade the technology stack versions.
    Also, we can incrementally adopt a newer technology without much
    difficulty.

-   **Reliability**: Microservices architecture can be very reliable. If
    one feature goes down, the entire application doesn't go down. We
    can fix the issue in the corresponding microservice and immediately
    deploy it.

-   **Development speed**: Development is pretty fast in microservices
    architecture. Since the volume of code is much less for a
    microservice, it's not difficult for new team members to understand
    and modify the code.

-   **Scalability**: Scalability is a major advantage in microservice
    architecture. Each microservice can be scaled individually. Since
    individual microservices are much smaller in size, caching becomes
    very effective.

## 

## Design Microservices architecture.

In our case we have a legacy application that offers some basic
functionalities such as managing bank transaction between clients and
enterprises.

The main difficulty resides in Modernizing, scaling, extending the
system to support new external partners, markets, or devices in order
satisfy customer's needs, to approach this situation we will adopt
Microservice architecture.

In order to implement our Microservice architecture, Spring Cloud which
is a collection of tools that provides solutions to some of the commonly
encountered patterns when building distributed systems.

Among the solutions provided by Spring Cloud, we will find
implementation for the following problems: API Gateway, Circuit Breaker,
Service Discovery.

## API Gateway

![](media/image2.png){width="2.782638888888889in"
height="1.6277777777777778in"}In a microservices architecture, each
microservice exposes a set of (typically) fine-grained endpoints. This
fact can impact the client-to-microservice communication, a possible
approach is to use a direct client-to-microservice communication
architecture. In this approach, a client app can make requests directly
to some of the microservices.

-   Problem:

the client apps usually need to consume functionality from more than one
microservice. If that consumption is performed directly, the client
needs to handle multiple calls to microservice endpoints. What happens
when the application evolves and new microservices are introduced or
existing microservices are updated? If your application has many
microservices, handling so many endpoints from the client apps can be a
nightmare. Since the client app would be coupled to those internal
endpoints, evolving the microservices in the future can cause high
impact for the client apps.

Therefore, having an intermediate level or tier of indirection (Gateway)
can be very convenient for microservice-based applications. If you
don\'t have API Gateways, the client apps must send requests directly to
the microservices and that raises problems, such as the following
issues:

-   Coupling: Without the API Gateway pattern, the client apps are
    coupled to the internal microservices. The client apps need to know
    how the multiple areas of the application are decomposed in
    microservices. When evolving and refactoring the internal
    microservices, those actions impact maintenance because they cause
    breaking changes to the client apps due to the direct reference to
    the internal microservices from the client apps. Client apps need to
    be updated frequently, making the solution harder to evolve.

-   Too many round trips: A single page/screen in the client app might
    require several calls to multiple services. That can result in
    multiple network round trips between the client and the server,
    adding significant latency. Aggregation handled in an intermediate
    level could improve the performance and user experience for the
    client app.

-   Security issues: Without a gateway, all the microservices must be
    exposed to the \"external world\", making the attack surface larger
    than if you hide internal microservices that aren\'t directly used
    by the client apps. The smaller the attack surface is, the more
    secure your application can be.

```{=html}
<!-- -->
```
-   ![](media/image3.png){width="2.720833333333333in"
    height="1.4909722222222221in"}Solution:

Implement an API gateway that is the single-entry point for all clients.
The API gateway handles requests in one of two ways. Some requests are
simply proxied/routed to the appropriate service. It handles other
requests by fanning out to multiple services.

Spring Cloud Gateway is API Gateway implementation by Spring Cloud team
on top of Spring reactive ecosystem. It provides a simple and effective
way to route incoming requests to the appropriate destination using
Gateway Handler Mapping.

And Spring Cloud Gateway uses Netty server to provide non-blocking
asynchronous request processing.

Spring Cloud Gateway consists of 3 main building blocks:

-   Route: Think of this as the destination that we want a particular
    request to route to. It comprises of destination URI, a condition
    that has to satisfy --- Or in terms of technical terms, Predicates,
    and one or more filters.

-   Predicate: This is literally a condition to match. i.e. kind of "if"
    condition. if requests have something --- e.g. path=blah or request
    header contains foo-bar etc. In technical terms, it is Java 8
    Function Predicate

-   Filter: These are instances of Spring Framework WebFilter. This is
    where you can apply your magic of modifying request or response.
    There are quite a lot of out of box WebFilter that framework
    provides.

## Service Discovery

-   Problem

How do clients of a service (in the case of Client-side discovery)
and/or routers (in the case of Server-side discovery) know about the
available instances of a service?

-   Forces

Each instance of a service exposes a remote API such as HTTP/REST, or
Thrift etc. at a particular location (host and port). The number of
services instances and their locations changes dynamically. Virtual
machines and containers are usually assigned a dynamic IP address. An
EC2 Autoscaling Group, for example, adjusts the number of instances
based on load.

-   Solution

Implement a service registry, which is a database of services, their
instances and their locations. Service instances are registered with the
service registry on startup and deregistered on shutdown. Client of the
service and/or routers query the service registry to find the available
instances of a service. A service registry might invoke a service
instance's health check API to verify that it is able to handle
requests.

Client-side service discovery via "*Spring Cloud Netflix Eureka"* allows
services to find and communicate with each other without hard-coding
hostname and port. The only 'fixed point\' in such an architecture
consists of a service registry with which each service has to register.

A drawback is that all clients must implement a certain logic to
interact with this fixed point. This assumes an additional network round
trip before the actual request.

With Netflix Eureka each client can simultaneously act as a server, to
replicate its status to a connected peer. In other words, a client
retrieves a list of all connected peers of a service registry and makes
all further requests to any other services through a load-balancing
algorithm.

To be informed about the presence of a client, they have to send a
heartbeat signal to the registry.

## Circuit Breaker

Services sometimes collaborate when handling requests. When one service
synchronously invokes another there is always the possibility that the
other service is unavailable or is exhibiting such high latency it is
essentially unusable. Precious resources such as threads might be
consumed in the caller while waiting for the other service to respond.
This might lead to resource exhaustion, which would make the calling
service unable to handle other requests. The failure of one service can
potentially cascade to other services throughout the application.

-   Problem

How to prevent a network or service failure from cascading to other
services?

-   Solution

Spring Cloud Netflix Hystrix -- the fault tolerance library. We'll use
the library and implement the Circuit Breaker enterprise pattern, which
is describing a strategy against failure cascading at different levels
in an application.

The principle is analogous to electronics: Hystrix is watching methods
for failing calls to related services. If there is such a failure, it
will open the circuit and forward the call to a fallback method.

The library will tolerate failures up to a threshold. Beyond that, it
leaves the circuit open. Which means, it will forward all subsequent
calls to the fallback method, to prevent future failures. This creates a
time buffer for the related service to recover from its failing state.

## Identity Management

IAM (Identity Access Management) is a framework used to authenticate the
user identity and privileges. It checks whether the users have access to
necessary files, networks and other resources that the user has
requested. It also checks how and by whom the information can be
accessed and modified by the management of descriptive information of
users. IAM systems provide tools and some technologies to the
administrators to change a user's role, keeping track on user activities
etc.

Keycloak is an open source identity and access management solution which
mainly aims at applications and services. Users can authenticate with
Keycloak rather than individual applications. So, the applications don't
have to deal with login forms, authenticating users and storing users.
Once logged-in to Keycloak, users don't have to login again to access
different applications. Same thing is applicable to sign-out. Keycloak
offers everything a sophisticated user management tool needs -- without
having to log on repeatedly with every login and into every system-as
well as system security, social logins, support for mobile apps and
integration into other solutions. Keycloak have implementations to LDAP
and Active Directory as well.

-   Keycloak Working Procedure

On a complete system secured with keycloak:

A user clicks from a public page to navigate to protected area within
the application. The link to this protected area is in the application
settings in keycloak admin console.

The user will be redirected indeed to the keycloak authentication page.
After providing username and password, keycloak redirects the user back
to the application again with a code that is valid to a very short span
of time.

![](media/image4.jpeg){width="3.7319444444444443in"
height="2.3645833333333335in"}The application communicates this code to
keycloak along with the application ID and the application secret, then
keycloak replies with the Access token, ID token, and a Refresh token.
Your application will need only one of these tokens to see which claims
the user has, and according to the claims, the user will be granted or
denied access to the requested protected URL(s).

# Implementing Microservices architecture.

As it is shown below, our architecture consists of three services:

-   "Suivie-Client" service composed of all the existing functionalities
    provided by the legacy application.

-   "Facturation" exposes some APIs that manipulate Bills

-   Currency Converter API which is provided by RAPID API

![](media/image5.png){width="6.21971019247594in"
height="2.069346019247594in"}

UML Diagram:

![](media/image6.png){width="5.651162510936133in"
height="3.770977690288714in"}

# Front end Application

1.  ## Angular

2.  ## Screenshot

![](media/image7.png){width="6.290972222222222in"
height="2.8722222222222222in"}

![](media/image8.png){width="6.290972222222222in"
height="2.848611111111111in"}

![](media/image9.png){width="6.290972222222222in" height="2.8375in"}

![](media/image10.png){width="6.290972222222222in"
height="2.7909722222222224in"}

![](media/image11.png){width="6.290972222222222in"
height="3.0347222222222223in"}
